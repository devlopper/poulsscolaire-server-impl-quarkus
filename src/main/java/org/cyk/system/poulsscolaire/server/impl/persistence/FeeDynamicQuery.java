package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableNamable;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.cyk.system.poulsscolaire.server.api.fee.AbstractAmountContainerDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeFilter;

/**
 * Cette classe représente la requête dynamique de {@link Fee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeDynamicQuery extends AbstractAmountContainerDynamicQuery<Fee> {

  String schoolVariableName;
  String periodVariableName;
  String branchVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public FeeDynamicQuery() {
    super(Fee.class);
    schoolVariableName = "school";
    periodVariableName = "period";
    branchVariableName = "branch";
  }

  @Override
  @PostConstruct
  void postConstruct() {
    super.postConstruct();
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(FeeDto.JSON_SCHOOLING_IDENTIFIER)
        .nameFieldName(Fee.FIELD_SCHOOLING_IDENTIFIER)
        .fieldName(fieldName(Fee.FIELD_SCHOOLING, AbstractIdentifiable.FIELD_IDENTIFIER)).build();

    projectionBuilder().name(FeeDto.JSON_SCHOOLING_SCHOOL_AS_STRING)
        .nameFieldName(Fee.FIELD_SCHOOLING_SCHOOL_AS_STRING).tupleVariableName(schoolVariableName)
        .fieldName(AbstractIdentifiableNamable.FIELD_NAME).build();

    projectionBuilder().name(FeeDto.JSON_SCHOOLING_PERIOD_AS_STRING)
        .nameFieldName(Fee.FIELD_SCHOOLING_PERIOD_AS_STRING).tupleVariableName(periodVariableName)
        .fieldName(AbstractIdentifiableNamable.FIELD_NAME).build();

    projectionBuilder().name(FeeDto.JSON_SCHOOLING_BRANCH_AS_STRING)
        .nameFieldName(Fee.FIELD_SCHOOLING_BRANCH_AS_STRING).tupleVariableName(branchVariableName)
        .fieldName(AbstractIdentifiableNamable.FIELD_NAME).build();

    projectionBuilder().name(FeeDto.JSON_ASSIGNMENT_TYPE_IDENTIFIER)
        .nameFieldName(Fee.FIELD_ASSIGNMENT_TYPE_IDENTIFIER)
        .fieldName(fieldName(Fee.FIELD_ASSIGNMENT_TYPE, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();
    projectionBuilder().name(FeeDto.JSON_ASSIGNMENT_TYPE_AS_STRING)
        .nameFieldName(Fee.FIELD_ASSIGNMENT_TYPE_AS_STRING)
        .fieldName(
            fieldName(Fee.FIELD_ASSIGNMENT_TYPE, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(FeeDto.JSON_SENIORITY_IDENTIFIER)
        .nameFieldName(Fee.FIELD_SENIORITY_IDENTIFIER)
        .fieldName(fieldName(Fee.FIELD_SENIORITY, AbstractIdentifiable.FIELD_IDENTIFIER)).build();
    projectionBuilder().name(FeeDto.JSON_SENIORITY_AS_STRING)
        .nameFieldName(Fee.FIELD_SENIORITY_AS_STRING)
        .fieldName(fieldName(Fee.FIELD_SENIORITY, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(FeeDto.JSON_CATEGORY_IDENTIFIER)
        .nameFieldName(Fee.FIELD_CATEGORY_IDENTIFIER)
        .fieldName(fieldName(Fee.FIELD_CATEGORY, AbstractIdentifiable.FIELD_IDENTIFIER)).build();
    projectionBuilder().name(FeeDto.JSON_CATEGORY_AS_STRING)
        .nameFieldName(Fee.FIELD_CATEGORY_AS_STRING)
        .fieldName(fieldName(Fee.FIELD_CATEGORY, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_VALUE)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_VALUE)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_VALUE)).build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_VALUE_AS_STRING)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_VALUE_AS_STRING)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_VALUE)).build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_REGISTRATION_VALUE_PART)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_REGISTRATION_VALUE_PART)
        .fieldName(
            fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_REGISTRATION_VALUE_PART))
        .build();
    projectionBuilder()
        .name(AbstractAmountContainerDto.JSON_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING)
        .fieldName(
            fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_REGISTRATION_VALUE_PART))
        .build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_PAYMENT_ORDER_NUMBER)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_PAYMENT_ORDER_NUMBER)
        .fieldName(
            fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_PAYMENT_ORDER_NUMBER))
        .build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_PAYMENT_ORDER_NUMBER_AS_STRING)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_PAYMENT_ORDER_NUMBER_AS_STRING)
        .fieldName(
            fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_PAYMENT_ORDER_NUMBER))
        .build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_OPTIONAL)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_OPTIONAL)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_OPTIONAL)).build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_OPTIONAL_AS_STRING)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_OPTIONAL_AS_STRING)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_OPTIONAL)).build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_RENEWABLE)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_RENEWABLE)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_RENEWABLE)).build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_RENEWABLE_AS_STRING)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_RENEWABLE_AS_STRING)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_RENEWABLE)).build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_DEADLINE_IDENTIFIER)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_DEADLINE_IDENTIFIER)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_DEADLINE,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_DEADLINE_AS_STRING)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_DEADLINE_AS_STRING)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_DEADLINE,
            AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    // Jointure
    joinBuilder().projectionsNames(FeeDto.JSON_SCHOOLING_SCHOOL_AS_STRING)
        .entityName(School.ENTITY_NAME).tupleVariableName(schoolVariableName)
        .parentFieldName(fieldName(Fee.FIELD_SCHOOLING, Schooling.FIELD_SCHOOL_IDENTIFIER))
        .leftInnerOrRight(true).build();

    joinBuilder().projectionsNames(FeeDto.JSON_SCHOOLING_PERIOD_AS_STRING)
        .entityName(Period.ENTITY_NAME).tupleVariableName(periodVariableName)
        .parentFieldName(fieldName(Fee.FIELD_SCHOOLING, Schooling.FIELD_PERIOD_IDENTIFIER))
        .leftInnerOrRight(true).build();

    joinBuilder().projectionsNames(FeeDto.JSON_SCHOOLING_BRANCH_AS_STRING)
        .entityName(Branch.ENTITY_NAME).tupleVariableName(branchVariableName)
        .parentFieldName(fieldName(Fee.FIELD_SCHOOLING, Schooling.FIELD_BRANCH_IDENTIFIER))
        .leftInnerOrRight(true).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(FeeFilter.JSON_SCHOOLING_IDENTIFIER)
        .fieldName(fieldName(Fee.FIELD_SCHOOLING, AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(FeeFilter::getSchoolingIdentifier).build();

    // Ordres par défaut
    // orderBuilder().fieldName(fieldName(Fee.FIELD_DEADLINE, Deadline.FIELD_DATE)).ascending(false)
    // .build();
  }
}
