package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableNamableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import ci.gouv.dgbf.extension.server.service.api.request.ProjectionDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Set;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryFilter;

/**
 * Cette classe représente la requête dynamique de {@link FeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeCategoryDynamicQuery extends AbstractDynamicQuery<FeeCategory> {

  @Inject
  @Getter
  EntityManager entityManager;

  @Inject
  AmountDynamicQuery amountDynamicQuery;

  @Inject
  PaymentAdjustedFeeDynamicQuery paymentAdjustedFeeDynamicQuery;

  String feeVariableName;
  String adjustedFeeVariableName;
  String registrationVariableName;
  String schoolingVariableName;

  String querySumPayment;

  String adjustedFeeAmountToPayVariableName;
  String adjustedFeeAmountPaidVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public FeeCategoryDynamicQuery() {
    super(FeeCategory.class);
    feeVariableName = "f";
    adjustedFeeVariableName = "af";
    registrationVariableName = "r";
    schoolingVariableName = "s";

    adjustedFeeAmountToPayVariableName = "afatp";
    adjustedFeeAmountPaidVariableName = "afap";
  }

  @PostConstruct
  void postConstruct() {
    querySumPayment = paymentAdjustedFeeDynamicQuery.formatSumAmountSubQuery(fieldName(
        PaymentAdjustedFee.FIELD_ADJUSTED_FEE, AdjustedFee.FIELD_FEE, Fee.FIELD_CATEGORY));

    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    projectionBuilder().name(FeeCategoryDto.JSON_TOTAL_AMOUNT_AS_STRING)
        .expression(formatSum(
            fieldName(adjustedFeeAmountToPayVariableName, AbstractAdjustedFeeAmount.FIELD_AMOUNT)))
        .resultConsumer((i, a) -> i.totalAmountAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(FeeCategoryDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING)
        .expression(formatSum(fieldName(adjustedFeeAmountToPayVariableName,
            AbstractAdjustedFeeAmount.FIELD_AMOUNT_REGISTRATION)))
        .resultConsumer((i, a) -> i.totalRegistrationAmountAsString = a.getNextAsLongFormatted())
        .build();

    projectionBuilder().name(FeeCategoryDto.JSON_PAID_AMOUNT_AS_STRING)
        .expression(formatSum(
            fieldName(adjustedFeeAmountPaidVariableName, AbstractAdjustedFeeAmount.FIELD_AMOUNT)))
        .resultConsumer((i, a) -> i.paidAmountAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(FeeCategoryDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING)
        .expression(formatSum(fieldName(adjustedFeeAmountPaidVariableName,
            AbstractAdjustedFeeAmount.FIELD_AMOUNT_REGISTRATION)))
        .resultConsumer((i, a) -> i.paidRegistrationAmountAsString = a.getNextAsLongFormatted())
        .build();

    projectionBuilder().name(FeeCategoryDto.JSON_PAYABLE_AMOUNT_AS_STRING)
        .expression(formatAmountPayable(AbstractAdjustedFeeAmount.FIELD_AMOUNT))
        .resultConsumer((i, a) -> i.payableAmountAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(FeeCategoryDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING)
        .expression(formatAmountPayable(AbstractAdjustedFeeAmount.FIELD_AMOUNT_REGISTRATION))
        .resultConsumer((i, a) -> i.payableRegistrationAmountAsString = a.getNextAsLongFormatted())
        .build();

    // Jointures
    joinBuilder().predicatesNames(FeeCategoryFilter.JSON_REGISTRATION_SCHOOLING_SCHOOL_IDENTIFIER)
        .with(Fee.class).tupleVariableName(feeVariableName).fieldName(Fee.FIELD_CATEGORY)
        .parentFieldName(null).leftInnerOrRight(true).build();

    joinBuilder().predicatesNames(FeeCategoryFilter.JSON_REGISTRATION_SCHOOLING_SCHOOL_IDENTIFIER)
        .with(AdjustedFee.class).tupleVariableName(adjustedFeeVariableName)
        .fieldName(AdjustedFee.FIELD_FEE).parentTupleVariableName(feeVariableName)
        .parentFieldName(null).leftInnerOrRight(true).build();

    joinBuilder().predicatesNames(FeeCategoryFilter.JSON_REGISTRATION_SCHOOLING_SCHOOL_IDENTIFIER)
        .with(Registration.class).tupleVariableName(registrationVariableName).fieldName(null)
        .parentTupleVariableName(adjustedFeeVariableName)
        .parentFieldName(AdjustedFee.FIELD_REGISTRATION).leftInnerOrRight(true).build();

    joinBuilder().predicatesNames(FeeCategoryFilter.JSON_REGISTRATION_SCHOOLING_SCHOOL_IDENTIFIER)
        .with(Schooling.class).tupleVariableName(schoolingVariableName).fieldName(null)
        .parentTupleVariableName(registrationVariableName)
        .parentFieldName(Registration.FIELD_SCHOOLING).leftInnerOrRight(true).build();

    joinBuilder()
        .projectionsNames(FeeCategoryDto.JSON_TOTAL_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_PAYABLE_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING)
        .with(AdjustedFeeAmountToPay.class).tupleVariableName(adjustedFeeAmountToPayVariableName)
        .fieldName(AbstractAdjustedFeeAmount.FIELD_FEE_CATEGORY_IDENTIFIER)
        .parentFieldName(AbstractIdentifiable.FIELD_IDENTIFIER).leftInnerOrRight(true).build();

    joinBuilder()
        .projectionsNames(FeeCategoryDto.JSON_PAID_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_PAYABLE_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING)
        .with(AdjustedFeeAmountPaid.class).tupleVariableName(adjustedFeeAmountPaidVariableName)
        .fieldName(AbstractAdjustedFeeAmount.FIELD_FEE_CATEGORY_IDENTIFIER)
        .parentFieldName(AbstractIdentifiable.FIELD_IDENTIFIER).leftInnerOrRight(true).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(FeeCategoryFilter.JSON_REGISTRATION_SCHOOLING_SCHOOL_IDENTIFIER)
        .tupleVariableName(schoolingVariableName).fieldName(Schooling.FIELD_SCHOOL_IDENTIFIER)
        .valueFunction(FeeCategoryFilter::getRegistrationSchoolingSchoolIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }

  String formatAmountPayable(String fieldName) {
    return String.format("COALESCE(%1$s.%3$s,0) - COALESCE(%2$s.%3$s,0)",
        adjustedFeeAmountToPayVariableName, adjustedFeeAmountPaidVariableName, fieldName);
  }

  @Override
  protected boolean isGrouped(DynamicQueryParameters<FeeCategory> parameters) {
    return ProjectionDto.hasOneOfNames(parameters.getProjection(),
        FeeCategoryDto.JSON_TOTAL_AMOUNT_AS_STRING, FeeCategoryDto.JSON_PAID_AMOUNT_AS_STRING,
        FeeCategoryDto.JSON_PAYABLE_AMOUNT_AS_STRING,
        FeeCategoryDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING,
        FeeCategoryDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING,
        FeeCategoryDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING);
  }

  @Override
  protected void buildGroups(DynamicQueryParameters<FeeCategory> parameters, Set<String> groups) {
    /*
     * On ajoute l'identifiant pour l'apsect technique de regroupement
     */
    groups.add(fieldName(variableName, AbstractIdentifiable.FIELD_IDENTIFIER));
    /*
     * On ajoute le code et le libellé pour permettre le tri par code et libellé
     */
    groups.add(fieldName(variableName, AbstractIdentifiableCodable.FIELD_CODE));
    groups.add(fieldName(variableName, AbstractIdentifiableCodableNamable.FIELD_NAME));
    /*
     * On ajoute les autres au besoin
     */
  }
}
