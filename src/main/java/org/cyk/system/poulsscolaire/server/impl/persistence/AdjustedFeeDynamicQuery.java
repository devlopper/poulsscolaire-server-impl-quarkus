package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.cyk.system.poulsscolaire.server.api.fee.AbstractAmountContainerDto;
import org.cyk.system.poulsscolaire.server.api.fee.AbstractAmountContainerFilter;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeFilter;

/**
 * Cette classe représente la requête dynamique de {@link AdjustedFee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeeDynamicQuery extends AbstractAmountContainerDynamicQuery<AdjustedFee> {

  String schoolVariableName;
  String branchVariableName;
  String periodVariableName;
  String adjustedFeeAmountsVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public AdjustedFeeDynamicQuery() {
    super(AdjustedFee.class);
    schoolVariableName = "s";
    branchVariableName = "b";
    periodVariableName = "p";
    adjustedFeeAmountsVariableName = "afa";
  }

  @Override
  @PostConstruct
  void postConstruct() {
    super.postConstruct();
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    buildFeeProjections();
    buildRegistrationProjections();
    buildAmountProjections();

    // Jointures
    buildJoins();

    // Prédicats
    buildPredicates();

    // Ordres par défaut
    orderBuilder().fieldName(fieldName(AdjustedFee.FIELD_FEE, Fee.FIELD_CATEGORY,
        AbstractIdentifiableCodableNamable.FIELD_NAME)).ascending(false).build();
    orderBuilder()
        .fieldName(
            fieldName(AdjustedFee.FIELD_REGISTRATION, AbstractIdentifiableCodable.FIELD_CODE))
        .ascending(false).build();
  }

  void buildFeeProjections() {
    projectionBuilder().name(AdjustedFeeDto.JSON_FEE_IDENTIFIER)
        .nameFieldName(AdjustedFee.FIELD_FEE_IDENTIFIER)
        .fieldName(fieldName(AdjustedFee.FIELD_FEE, AbstractIdentifiable.FIELD_IDENTIFIER)).build();

    projectionBuilder().name(AdjustedFeeDto.JSON_FEE_AS_STRING)
        .nameFieldName(AdjustedFee.FIELD_FEE_AS_STRING).fieldName(fieldName(AdjustedFee.FIELD_FEE,
            Fee.FIELD_CATEGORY, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(AdjustedFeeDto.JSON_FEE_OPTIONAL)
        .nameFieldName(AdjustedFee.FIELD_FEE_OPTIONAL).fieldName(fieldName(AdjustedFee.FIELD_FEE,
            AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_OPTIONAL))
        .build();

    projectionBuilder().name(AdjustedFeeDto.JSON_FEE_CATEGORY_AS_STRING)
        .expression(formatConcatName(fieldName(AdjustedFee.FIELD_FEE, Fee.FIELD_CATEGORY)))
        .resultConsumer((i, a) -> i.feeCategoryAsString = a.getNextAsString()).build();
  }

  void buildRegistrationProjections() {
    projectionBuilder().name(AdjustedFeeDto.JSON_REGISTRATION_IDENTIFIER)
        .nameFieldName(AdjustedFee.FIELD_REGISTRATION_IDENTIFIER)
        .fieldName(fieldName(AdjustedFee.FIELD_REGISTRATION, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();
    projectionBuilder().name(AdjustedFeeDto.JSON_REGISTRATION_AS_STRING)
        .expression("CONCAT(t.registration.code,' (',t.registration.student.identity.firstName"
            + ",' ',t.registration.student.identity.lastNames,')')")
        .resultConsumer((i, a) -> i.registrationAsString = a.getNextAsString()).build();
    projectionBuilder().name(AdjustedFeeDto.JSON_REGISTRATION_STUDENT_AS_STRING)
        .expression(formatConcat("t.registration.student.identity.firstName", "' '",
            "t.registration.student.identity.lastNames"))
        .resultConsumer((i, a) -> i.registrationStudentAsString = a.getNextAsString()).build();

    projectionBuilder().name(AdjustedFeeDto.JSON_REGISTRATION_SCHOOLING_SCHOOL_AS_STRING)
        .expression(formatConcatName(schoolVariableName))
        .resultConsumer((i, a) -> i.registrationSchoolingSchoolAsString = a.getNextAsString())
        .build();

    projectionBuilder().name(AdjustedFeeDto.JSON_REGISTRATION_SCHOOLING_BRANCH_AS_STRING)
        .expression(formatConcatName(branchVariableName))
        .resultConsumer((i, a) -> i.registrationSchoolingBranchAsString = a.getNextAsString())
        .build();

    projectionBuilder().name(AdjustedFeeDto.JSON_REGISTRATION_SCHOOLING_PERIOD_AS_STRING)
        .expression(formatConcatName(periodVariableName))
        .resultConsumer((i, a) -> i.registrationSchoolingPeriodAsString = a.getNextAsString())
        .build();
  }

  void buildAmountProjections() {
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_VALUE)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_VALUE)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_VALUE)).build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_VALUE_AS_STRING)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_VALUE_AS_STRING)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_VALUE)).build();

    projectionBuilder().name(AdjustedFeeDto.JSON_AMOUNT_VALUE_TO_PAY_AS_STRING)
        .nameFieldName(AdjustedFee.FIELD_AMOUNT_VALUE_TO_PAY_AS_STRING)
        .tupleVariableName(adjustedFeeAmountsVariableName)
        .fieldName(AdjustedFeeAmounts.FIELD_AMOUNT_TO_PAY).build();

    projectionBuilder().name(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID_AS_STRING)
        .nameFieldName(AdjustedFee.FIELD_AMOUNT_VALUE_PAID_AS_STRING)
        .tupleVariableName(adjustedFeeAmountsVariableName)
        .fieldName(AdjustedFeeAmounts.FIELD_AMOUNT_PAID).build();

    projectionBuilder().name(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAYABLE_AS_STRING)
        .nameFieldName(AdjustedFee.FIELD_AMOUNT_VALUE_PAYABLE_AS_STRING)
        .tupleVariableName(adjustedFeeAmountsVariableName)
        .fieldName(AdjustedFeeAmounts.FIELD_AMOUNT_LEFT_TO_PAY).build();

    projectionBuilder().name(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAYABLE)
        .nameFieldName(AdjustedFee.FIELD_AMOUNT_VALUE_PAYABLE)
        .tupleVariableName(adjustedFeeAmountsVariableName)
        .fieldName(AdjustedFeeAmounts.FIELD_AMOUNT_LEFT_TO_PAY).build();

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
  }

  void buildJoins() {
    joinBuilder().projectionsNames(AdjustedFeeDto.JSON_REGISTRATION_SCHOOLING_SCHOOL_AS_STRING)
        .entityName(School.ENTITY_NAME).tupleVariableName(schoolVariableName)
        .parentFieldName(fieldName(AdjustedFee.FIELD_FEE, Fee.FIELD_SCHOOLING,
            Schooling.FIELD_SCHOOL_IDENTIFIER))
        .leftInnerOrRight(true).build();

    joinBuilder().projectionsNames(AdjustedFeeDto.JSON_REGISTRATION_SCHOOLING_PERIOD_AS_STRING)
        .entityName(Period.ENTITY_NAME).tupleVariableName(periodVariableName)
        .parentFieldName(fieldName(AdjustedFee.FIELD_FEE, Fee.FIELD_SCHOOLING,
            Schooling.FIELD_PERIOD_IDENTIFIER))
        .leftInnerOrRight(true).build();

    joinBuilder().projectionsNames(AdjustedFeeDto.JSON_REGISTRATION_SCHOOLING_BRANCH_AS_STRING)
        .entityName(Branch.ENTITY_NAME).tupleVariableName(branchVariableName)
        .parentFieldName(fieldName(AdjustedFee.FIELD_FEE, Fee.FIELD_SCHOOLING,
            Schooling.FIELD_BRANCH_IDENTIFIER))
        .leftInnerOrRight(true).build();

    joinBuilder()
        .projectionsNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_TO_PAY,
            AdjustedFeeDto.JSON_AMOUNT_VALUE_TO_PAY_AS_STRING,
            AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID, AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID_AS_STRING,
            AdjustedFeeDto.JSON_AMOUNT_VALUE_PAYABLE,
            AdjustedFeeDto.JSON_AMOUNT_VALUE_PAYABLE_AS_STRING)
        .predicatesNames(
            AbstractAmountContainerFilter.JSON_AMOUNT_VALUE_PAYABLE_LESS_THAN_OR_EQUALS_ZERO)
        .with(AdjustedFeeAmounts.class).tupleVariableName(adjustedFeeAmountsVariableName)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .parentFieldName(AbstractIdentifiable.FIELD_IDENTIFIER).leftInnerOrRight(true).build();
  }

  void buildPredicates() {
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(AdjustedFeeFilter.JSON_REGISTRATION_SCHOOLING_SCHOOL_IDENTIFIER)
        .tupleVariableName(schoolVariableName).fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AdjustedFeeFilter::getRegistrationSchoolingSchoolIdentifier).build();

    predicateBuilder().name(AdjustedFeeFilter.JSON_REGISTRATION_SCHOOLING_BRANCH_IDENTIFIER)
        .tupleVariableName(branchVariableName).fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AdjustedFeeFilter::getRegistrationSchoolingBranchIdentifier).build();

    predicateBuilder().name(AdjustedFeeFilter.JSON_REGISTRATION_SCHOOLING_PERIOD_IDENTIFIER)
        .tupleVariableName(periodVariableName).fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AdjustedFeeFilter::getRegistrationSchoolingPeriodIdentifier).build();

    predicateBuilder().name(AdjustedFeeFilter.JSON_REGISTRATION_STUDENT_IDENTIFIER)
        .fieldName(fieldName(AdjustedFee.FIELD_REGISTRATION, Registration.FIELD_STUDENT,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(AdjustedFeeFilter::getRegistrationStudentIdentifier).build();

    predicateBuilder().name(AdjustedFeeFilter.JSON_REGISTRATION_IDENTIFIER)
        .fieldName(fieldName(AdjustedFee.FIELD_REGISTRATION, AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(AdjustedFeeFilter::getRegistrationIdentifier).build();

    predicateBuilder().name(AdjustedFeeFilter.JSON_FEE_IDENTIFIER)
        .fieldName(fieldName(AdjustedFee.FIELD_FEE, AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(AdjustedFeeFilter::getFeeIdentifier).build();

    predicateBuilder().name(AdjustedFeeFilter.JSON_FEE_CATEGORY_IDENTIFIER)
        .fieldName(fieldName(AdjustedFee.FIELD_FEE, Fee.FIELD_CATEGORY,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(AdjustedFeeFilter::getFeeCategoryIdentifier).build();

    predicateBuilder()
        .name(AbstractAmountContainerFilter.JSON_AMOUNT_VALUE_PAYABLE_LESS_THAN_OR_EQUALS_ZERO)
        .tupleVariableName(adjustedFeeAmountsVariableName)
        .fieldName(AdjustedFeeAmounts.FIELD_AMOUNT_LEFT_TO_PAY_LESS_THAN_OR_EQUALS_ZERO)
        .valueFunction(AdjustedFeeFilter::getAmountValuePayableLessThanOrEqualsZero).build();

    predicateBuilder().name(AdjustedFeeFilter.JSON_LATE_PAYMENT)
        .tupleVariableName(adjustedFeeAmountsVariableName)
        .fieldName(AdjustedFeeAmounts.FIELD_LATE_PAYMENT)
        .valueFunction(AdjustedFeeFilter::getLatePayment).build();
  }
}
