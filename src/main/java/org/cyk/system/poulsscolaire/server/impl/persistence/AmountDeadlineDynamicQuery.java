package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineDto;

/**
 * Cette classe représente la requête dynamique de {@link AmountDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountDeadlineDynamicQuery
    extends AbstractDynamicQuery<AmountDeadline> {

  @Inject
  @Getter
  EntityManager entityManager;

  String branchVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public AmountDeadlineDynamicQuery() {
    super(AmountDeadline.class);
    branchVariableName = "b";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AmountDeadlineDto.JSON_FEE_CATEGORY_AS_STRING)
        .expression(formatConcatName(
            fieldName(AmountDeadline.FIELD_ADJUSTED_FEE, AdjustedFee.FIELD_FEE,
                Fee.FIELD_CATEGORY)))
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

    projectionBuilder().name(AmountDeadlineDto.JSON_DEADLINE_AS_STRING)
        .expression(fieldName(AmountDeadline.FIELD_DEADLINE,
            AbstractIdentifiableCodableNamable.FIELD_NAME))
        .resultConsumer((i, a) -> i.deadlineAsString = a.getNextAsString()).build();

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

    // Prédicats
    buildPredicates();

    // Ordres par défaut
  }



  void buildPredicates() {
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();
  }
}
