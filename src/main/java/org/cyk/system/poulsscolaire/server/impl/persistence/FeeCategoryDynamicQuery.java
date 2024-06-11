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

  String feeVariableName;
  String registrationVariableName;
  String adjustedFeeVariableName;
  String amountVariableName;
  String paymentAdjustedFeeVariableName;

  String querySumAdjustedFeeAmount;
  String querySumAdjustedFeeRegistrationAmount;
  String querySumPayment;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public FeeCategoryDynamicQuery() {
    super(FeeCategory.class);
    feeVariableName = "f";
    registrationVariableName = "r";
    adjustedFeeVariableName = "af";
    amountVariableName = "a";
    paymentAdjustedFeeVariableName = "paf";

    querySumPayment = formatValueOrZeroIfNull("(SELECT " + formatSum("v.amount")
        + " FROM PaymentAdjustedFee v WHERE v.adjustedFee.fee.category = t)");
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    projectionBuilder().name(FeeCategoryDto.JSON_TOTAL_AMOUNT_AS_STRING)
        .tupleVariableName(amountVariableName)
        .expression(amountDynamicQuery.formatValueSum(amountVariableName))
        .resultConsumer((i, a) -> i.totalAmountAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(FeeCategoryDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING)
        .tupleVariableName(amountVariableName)
        .expression(amountDynamicQuery.formatRegistrationValuePartSum(amountVariableName))
        .resultConsumer((i, a) -> i.totalRegistrationAmountAsString = a.getNextAsLongFormatted())
        .build();

    projectionBuilder().name(FeeCategoryDto.JSON_PAID_AMOUNT_AS_STRING)
        .expressionFunction(p -> getSumPaymentQuery(p.projection()))
        .resultConsumer((i, a) -> i.paidAmountAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(FeeCategoryDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING)
        .expression(String.format("(CASE WHEN %1$s >= %2$s THEN %2$s ELSE %1$s END)",
            amountDynamicQuery.formatRegistrationValuePartSum(amountVariableName), querySumPayment))
        .resultConsumer((i, a) -> i.paidRegistrationAmountAsString = a.getNextAsLongFormatted())
        .build();

    projectionBuilder().name(FeeCategoryDto.JSON_PAYABLE_AMOUNT_AS_STRING)
        .expression(String.format("(%s - %s)",
            amountDynamicQuery.formatValueSum(amountVariableName), querySumPayment))
        .resultConsumer((i, a) -> i.payableAmountAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(FeeCategoryDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING)
        .expression(String.format("(CASE WHEN %1$s >= %2$s THEN %1$s - %2$s ELSE 0 END)",
            amountDynamicQuery.formatRegistrationValuePartSum(amountVariableName), querySumPayment))
        .resultConsumer((i, a) -> i.payableRegistrationAmountAsString = a.getNextAsLongFormatted())
        .build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Jointures
    joinBuilder()
        .projectionsNames(FeeCategoryDto.JSON_TOTAL_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_PAID_AMOUNT_AS_STRING, FeeCategoryDto.JSON_PAYABLE_AMOUNT_AS_STRING)
        .with(Fee.class).tupleVariableName(feeVariableName).fieldName(Fee.FIELD_CATEGORY)
        .parentFieldName(null).leftInnerOrRight(true).build();

    joinBuilder()
        .projectionsNames(FeeCategoryDto.JSON_TOTAL_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_PAID_AMOUNT_AS_STRING, FeeCategoryDto.JSON_PAYABLE_AMOUNT_AS_STRING)
        .with(AdjustedFee.class).tupleVariableName(adjustedFeeVariableName)
        .fieldName(AdjustedFee.FIELD_FEE).parentTupleVariableName(feeVariableName)
        .parentFieldName(null).leftInnerOrRight(true).build();

    joinBuilder()
        .projectionsNames(FeeCategoryDto.JSON_TOTAL_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING)
        .with(Amount.class).tupleVariableName(amountVariableName)
        .parentTupleVariableName(adjustedFeeVariableName)
        .parentFieldName(AbstractAmountContainer.FIELD_AMOUNT).leftInnerOrRight(true).build();

    joinBuilder().disabledFunction(p -> hasTotalProjection(p.projection()))
        .projectionsNames(FeeCategoryDto.JSON_PAID_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_PAYABLE_AMOUNT_AS_STRING,
            FeeCategoryDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING)
        .with(PaymentAdjustedFee.class).tupleVariableName(paymentAdjustedFeeVariableName)
        .fieldName(PaymentAdjustedFee.FIELD_ADJUSTED_FEE)
        .parentTupleVariableName(adjustedFeeVariableName).parentFieldName(null)
        .leftInnerOrRight(true).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }

  String getSumPaymentQuery(ProjectionDto projection) {
    return hasTotalProjection(projection) ? querySumPayment
        : formatSum(formatValueOrZeroIfNull(paymentAdjustedFeeVariableName,
            PaymentAdjustedFee.FIELD_AMOUNT));
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

  boolean hasTotalProjection(ProjectionDto projection) {
    return ProjectionDto.hasOneOfNames(projection, FeeCategoryDto.JSON_TOTAL_AMOUNT_AS_STRING,
        FeeCategoryDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING);
  }
}
