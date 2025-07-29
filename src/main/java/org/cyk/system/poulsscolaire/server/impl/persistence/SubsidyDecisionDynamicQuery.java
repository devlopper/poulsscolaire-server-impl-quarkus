package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableValueBasedQuery;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionFilter;

/**
 * Cette classe représente la requête dynamique de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionDynamicQuery extends AbstractDynamicQuery<SubsidyDecision> {

  @Inject
  @Getter
  EntityManager entityManager;

  String schoolVariableName;
  String branchVariableName;
  String schoolingVariableName;
  String subsidyDecisionPaymentsVariableName;
  String subsidyDecisionRegistrationsVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public SubsidyDecisionDynamicQuery() {
    super(SubsidyDecision.class);
    schoolVariableName = "school";
    branchVariableName = "branch";
    schoolingVariableName = "schooling";
    subsidyDecisionPaymentsVariableName = "subsidyDecisionPayments";
    subsidyDecisionRegistrationsVariableName = "subsidyDecisionRegistrations";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableDto.JSON_AS_STRING)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE)
        .nameFieldName(AbstractIdentifiable.FIELD_AS_STRING).build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_AMOUNT).fieldName(SubsidyDecision.FIELD_AMOUNT)
        .build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_SCHOOLING_IDENTIFIER)
        .fieldName(
            fieldName(SubsidyDecision.FIELD_SCHOOLING, AbstractIdentifiable.FIELD_IDENTIFIER))
        .nameFieldName(SubsidyDecision.FIELD_SCHOOLING_IDENTIFIER).build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_SCHOOLING_AS_STRING)
        .tupleVariableName(branchVariableName)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME)
        .nameFieldName(SubsidyDecision.FIELD_SCHOOLING_AS_STRING).build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_SCHOOL_IDENTIFIER)
        .nameFieldName(SubsidyDecision.FIELD_SCHOOL_IDENTIFIER)
        .tupleVariableName(schoolingVariableName).fieldName(Schooling.FIELD_SCHOOL_IDENTIFIER)
        .build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_AMOUNT_AS_STRING)
        .fieldName(SubsidyDecision.FIELD_AMOUNT)
        .nameFieldName(SubsidyDecision.FIELD_AMOUNT_AS_STRING).build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_REGISTRATION_COUNT_AS_STRING)
        .nameFieldName(SubsidyDecision.FIELD_REGISTRATION_COUNT_AS_STRING)
        .tupleVariableName(subsidyDecisionRegistrationsVariableName)
        .fieldName(AbstractIdentifiableValueBasedQuery.FIELD_VALUE).nullValueIsZeroNumberString()
        .build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_PAYMENT_COUNT_AS_STRING)
        .nameFieldName(SubsidyDecision.FIELD_PAYMENT_COUNT_AS_STRING)
        .tupleVariableName(subsidyDecisionPaymentsVariableName)
        .fieldName(SubsidyDecisionPayments.FIELD_COUNT).nullValueIsZeroNumberString().build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_PAID_AMOUNT_AS_STRING)
        .nameFieldName(SubsidyDecision.FIELD_PAID_AMOUNT_AS_STRING)
        .tupleVariableName(subsidyDecisionPaymentsVariableName)
        .fieldName(SubsidyDecisionPayments.FIELD_PAID_AMOUNT).nullValueIsZeroNumberString().build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_REMAINING_AMOUNT_TO_PAY_AS_STRING)
        .nameFieldName(SubsidyDecision.FIELD_REMAINING_AMOUNT_TO_PAY_AS_STRING)
        .tupleVariableName(subsidyDecisionPaymentsVariableName)
        .fieldName(SubsidyDecisionPayments.FIELD_REMAINING_AMOUNT_TO_PAY)
        .nullValueIsZeroNumberString().build();

    // Jointures
    joinBuilder().projectionsNames(SubsidyDecisionDto.JSON_SCHOOLING_AS_STRING)
        .entityClass(Branch.class).tupleVariableName(branchVariableName).parentFieldName(
            fieldName(SubsidyDecision.FIELD_SCHOOLING, Schooling.FIELD_BRANCH_IDENTIFIER))
        .build();

    joinBuilder().projectionsNames(SubsidyDecisionDto.JSON_SCHOOL_IDENTIFIER)
        .entityClass(Schooling.class).tupleVariableName(schoolingVariableName)
        .parentFieldName(
            fieldName(SubsidyDecision.FIELD_SCHOOLING, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();

    joinBuilder()
        .projectionsNames(SubsidyDecisionDto.JSON_PAYMENT_COUNT_AS_STRING,
            SubsidyDecisionDto.JSON_PAID_AMOUNT_AS_STRING,
            SubsidyDecisionDto.JSON_REMAINING_AMOUNT_TO_PAY_AS_STRING)
        .leftInnerOrRight(true).entityClass(SubsidyDecisionPayments.class)
        .tupleVariableName(subsidyDecisionPaymentsVariableName).build();

    joinBuilder().projectionsNames(SubsidyDecisionDto.JSON_REGISTRATION_COUNT_AS_STRING)
        .leftInnerOrRight(true).entityClass(SubsidyDecisionRegistrations.class)
        .tupleVariableName(subsidyDecisionRegistrationsVariableName).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(SubsidyDecisionFilter.JSON_SCHOOL_IDENTIFIER)
        .fieldName(fieldName(SubsidyDecision.FIELD_SCHOOLING, Schooling.FIELD_SCHOOL_IDENTIFIER))
        .valueFunction(SubsidyDecisionFilter::getSchoolIdentifier).build();

    predicateBuilder().name(SubsidyDecisionFilter.JSON_SCHOOLING_IDENTIFIER)
        .fieldName(
            fieldName(SubsidyDecision.FIELD_SCHOOLING, AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(SubsidyDecisionFilter::getSchoolingIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }
}
