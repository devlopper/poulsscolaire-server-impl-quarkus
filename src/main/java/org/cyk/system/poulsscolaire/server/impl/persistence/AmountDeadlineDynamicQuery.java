package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineDto;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineFilter;

/**
 * Cette classe représente la requête dynamique de {@link AmountDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountDeadlineDynamicQuery extends AbstractDynamicQuery<AmountDeadline> {

  @Inject
  @Getter
  EntityManager entityManager;

  String branchVariableName;
  String feeVariableName;
  String adustedFeeVariableName;
  String statusesVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public AmountDeadlineDynamicQuery() {
    super(AmountDeadline.class);
    branchVariableName = "b";
    feeVariableName = "f";
    adustedFeeVariableName = "af";
    statusesVariableName = "s";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AmountDeadlineDto.JSON_AMOUNT_IDENTIFIER)
        .expression(fieldName(AmountDeadline.FIELD_AMOUNT, AbstractIdentifiable.FIELD_IDENTIFIER))
        .resultConsumer((i, a) -> i.amountIdentifier = a.getNextAsString()).build();

    projectionBuilder().name(AmountDeadlineDto.JSON_FEE_CATEGORY_AS_STRING)
        .expression(formatConcatName(fieldName(AmountDeadline.FIELD_ADJUSTED_FEE,
            AdjustedFee.FIELD_FEE, Fee.FIELD_CATEGORY)))
        .resultConsumer((i, a) -> i.feeCategoryAsString = a.getNextAsString()).build();

    projectionBuilder().name(AmountDeadlineDto.JSON_BRANCH_AS_STRING)
        .expression(formatConcatName(fieldName(branchVariableName)))
        .resultConsumer((i, a) -> i.branchAsString = a.getNextAsString()).build();

    projectionBuilder().name(AmountDeadlineDto.JSON_STUDENT_AS_STRING)
        .expression(formatConcat(
            fieldName(AmountDeadline.FIELD_ADJUSTED_FEE, AdjustedFee.FIELD_REGISTRATION,
                Registration.FIELD_STUDENT, Student.FIELD_IDENTITY, Identity.FIELD_FIRST_NAME),
            "' '",
            fieldName(AmountDeadline.FIELD_ADJUSTED_FEE, AdjustedFee.FIELD_REGISTRATION,
                Registration.FIELD_STUDENT, Student.FIELD_IDENTITY, Identity.FIELD_LAST_NAMES)))
        .resultConsumer((i, a) -> i.studentAsString = a.getNextAsString()).build();

    projectionBuilder().name(AmountDeadlineDto.JSON_DEADLINE_IDENTIFIER)
        .expression(fieldName(AmountDeadline.FIELD_DEADLINE, AbstractIdentifiable.FIELD_IDENTIFIER))
        .resultConsumer((i, a) -> i.deadlineIdentifier = a.getNextAsString()).build();

    projectionBuilder().name(AmountDeadlineDto.JSON_DEADLINE_AS_STRING)
        .expression(String.format("%s,%s.%s",
            formatConcatName(fieldName(variableName, AmountDeadline.FIELD_DEADLINE)), variableName,
            fieldName(AmountDeadline.FIELD_DEADLINE, Deadline.FIELD_DATE)))
        .resultConsumer((i, a) -> i.deadlineAsString =
            Deadline.format(a.getNextAsString(), a.getNextAsLocalDateTimeFormatted()))
        .build();

    projectionBuilder().name(AmountDeadlineDto.JSON_DEADLINE_PASSED)
        .nameFieldName(AmountDeadline.FIELD_DEADLINE_PASSED).tupleVariableName(statusesVariableName)
        .fieldName(AmountDeadlineStatuses.FIELD_PASSED).build();

    projectionBuilder().name(AmountDeadlineDto.JSON_DEADLINE_RUNNING)
        .nameFieldName(AmountDeadline.FIELD_DEADLINE_RUNNING)
        .tupleVariableName(statusesVariableName).fieldName(AmountDeadlineStatuses.FIELD_RUNNING)
        .build();

    projectionBuilder().name(AmountDeadlineDto.JSON_PAYMENT).fieldName(AmountDeadline.FIELD_PAYMENT)
        .build();

    projectionBuilder().name(AmountDeadlineDto.JSON_PAYMENT_AS_STRING)
        .nameFieldName(AmountDeadline.FIELD_PAYMENT_AS_STRING)
        .fieldName(AmountDeadline.FIELD_PAYMENT).build();

    // Jointures
    joinBuilder().projectionsNames(AmountDeadlineDto.JSON_BRANCH_AS_STRING)
        .entityName(Branch.ENTITY_NAME).tupleVariableName(branchVariableName)
        .parentFieldName(
            fieldName(AmountDeadline.FIELD_ADJUSTED_FEE, AdjustedFee.FIELD_REGISTRATION,
                Registration.FIELD_SCHOOLING, Schooling.FIELD_BRANCH_IDENTIFIER))
        .leftInnerOrRight(true).build();

    joinBuilder().predicatesNames(AmountDeadlineFilter.JSON_FEE_IDENTIFIER).entityClass(Fee.class)
        .tupleVariableName(feeVariableName).fieldName(AbstractAmountContainer.FIELD_AMOUNT)
        .parentFieldName(AmountDeadline.FIELD_AMOUNT).leftInnerOrRight(true).build();

    joinBuilder()
        .projectionsNames(AmountDeadlineDto.JSON_DEADLINE_PASSED,
            AmountDeadlineDto.JSON_DEADLINE_RUNNING)
        .entityClass(AmountDeadlineStatuses.class).tupleVariableName(statusesVariableName)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .parentFieldName(AbstractIdentifiable.FIELD_IDENTIFIER).leftInnerOrRight(true).build();

    // Prédicats
    buildPredicates();

    // Ordres par défaut
    orderBuilder().fieldName(fieldName(AmountDeadline.FIELD_DEADLINE, Deadline.FIELD_DATE))
        .ascending(true).build();
  }

  void buildPredicates() {
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(AmountDeadlineFilter.JSON_AMOUNT_IDENTIFIER)
        .fieldName(fieldName(AmountDeadline.FIELD_AMOUNT, AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(AmountDeadlineFilter::getAmountIdentifier).build();

    predicateBuilder().name(AmountDeadlineFilter.JSON_DEADLINE_IDENTIFIER)
        .fieldName(fieldName(AmountDeadline.FIELD_DEADLINE, AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(AmountDeadlineFilter::getDeadlineIdentifier).build();

    predicateBuilder().name(AmountDeadlineFilter.JSON_FEE_IDENTIFIER)
        .tupleVariableName(feeVariableName).fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AmountDeadlineFilter::getFeeIdentifier).build();
  }
}
