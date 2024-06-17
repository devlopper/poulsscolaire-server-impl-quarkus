package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import ci.gouv.dgbf.extension.server.service.api.request.ProjectionDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Set;
import org.cyk.system.poulsscolaire.server.api.fee.AbstractAmountContainerDto;
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

  String paymentAdjustedFeeVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public AdjustedFeeDynamicQuery() {
    super(AdjustedFee.class);
    paymentAdjustedFeeVariableName = "p";
  }

  @Override
  @PostConstruct
  void postConstruct() {
    super.postConstruct();
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_VALUE)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_VALUE)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_VALUE)).build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_VALUE_AS_STRING)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_VALUE_AS_STRING)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_VALUE)).build();

    projectionBuilder().name(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID_AS_STRING)
        .tupleVariableName(paymentAdjustedFeeVariableName)
        .expression(String.format("SUM(COALESCE(%s.%s, 0))", paymentAdjustedFeeVariableName,
            PaymentAdjustedFee.FIELD_AMOUNT))
        .resultConsumer((i, a) -> i.amountValuePaidAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(AdjustedFeeDto.JSON_AMOUNT_VALUE_LEFT_TO_PAY_AS_STRING)
        .tupleVariableName(paymentAdjustedFeeVariableName)
        .expression(String.format(
            "CASE WHEN %1$s.%2$s THEN 0 ELSE COALESCE(%1$s.%3$s,0) END"
                + " - SUM(COALESCE(%4$s.%5$s,0))",
            variableName, fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_OPTIONAL),
            fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_VALUE),
            paymentAdjustedFeeVariableName, PaymentAdjustedFee.FIELD_AMOUNT))
        .resultConsumer((i, a) -> i.amountValueLeftToPayAsString = a.getNextAsLongFormatted())
        .build();

    projectionBuilder().name(AdjustedFeeDto.JSON_FEE_IDENTIFIER)
        .nameFieldName(AdjustedFee.FIELD_FEE_IDENTIFIER)
        .fieldName(fieldName(AdjustedFee.FIELD_FEE, AbstractIdentifiable.FIELD_IDENTIFIER)).build();
    projectionBuilder().name(AdjustedFeeDto.JSON_FEE_AS_STRING)
        .nameFieldName(AdjustedFee.FIELD_FEE_AS_STRING).fieldName(fieldName(AdjustedFee.FIELD_FEE,
            Fee.FIELD_CATEGORY, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();
    projectionBuilder().name(AdjustedFeeDto.JSON_FEE_OPTIONAL)
        .nameFieldName(AdjustedFee.FIELD_FEE_OPTIONAL)
        .fieldName(fieldName(AdjustedFee.FIELD_FEE, Fee.FIELD_AMOUNT, Amount.FIELD_OPTIONAL))
        .build();

    projectionBuilder().name(AdjustedFeeDto.JSON_REGISTRATION_IDENTIFIER)
        .nameFieldName(AdjustedFee.FIELD_REGISTRATION_IDENTIFIER)
        .fieldName(fieldName(AdjustedFee.FIELD_REGISTRATION, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();
    projectionBuilder().name(AdjustedFeeDto.JSON_REGISTRATION_AS_STRING)
        .nameFieldName(AdjustedFee.FIELD_REGISTRATION_AS_STRING)
        .fieldName(
            fieldName(AdjustedFee.FIELD_REGISTRATION, AbstractIdentifiableCodable.FIELD_CODE))
        .build();

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

    // Jointures
    joinBuilder()
        .projectionsNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID,
            AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID_AS_STRING,
            AdjustedFeeDto.JSON_AMOUNT_VALUE_LEFT_TO_PAY,
            AdjustedFeeDto.JSON_AMOUNT_VALUE_LEFT_TO_PAY_AS_STRING)
        .with(PaymentAdjustedFee.class).tupleVariableName(paymentAdjustedFeeVariableName)
        .fieldName(PaymentAdjustedFee.FIELD_ADJUSTED_FEE).parentFieldName(null)
        .leftInnerOrRight(true).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(AdjustedFeeFilter.JSON_REGISTRATION_IDENTIFIER)
        .fieldName(fieldName(AdjustedFee.FIELD_REGISTRATION, AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(AdjustedFeeFilter::getRegistrationIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(fieldName(AdjustedFee.FIELD_FEE, Fee.FIELD_CATEGORY,
        AbstractIdentifiableCodableNamable.FIELD_NAME)).ascending(false).build();
    orderBuilder()
        .fieldName(
            fieldName(AdjustedFee.FIELD_REGISTRATION, AbstractIdentifiableCodable.FIELD_CODE))
        .ascending(false).build();
  }

  @Override
  protected boolean isGrouped(DynamicQueryParameters<AdjustedFee> parameters) {
    return ProjectionDto.hasOneOfNames(parameters.getProjection(),
        AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID, AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID_AS_STRING,
        AdjustedFeeDto.JSON_AMOUNT_VALUE_LEFT_TO_PAY,
        AdjustedFeeDto.JSON_AMOUNT_VALUE_LEFT_TO_PAY_AS_STRING);
  }

  @Override
  protected void buildGroups(DynamicQueryParameters<AdjustedFee> parameters, Set<String> groups) {
    /*
     * On ajoute l'identifiant pour l'apsect technique de regroupement
     */
    groups.add(fieldName(variableName, AbstractIdentifiable.FIELD_IDENTIFIER));
    /*
     * On ajoute les éléments d'ordre
     */
    groups.add(fieldName(variableName, AdjustedFee.FIELD_FEE, Fee.FIELD_CATEGORY,
        AbstractIdentifiableCodableNamable.FIELD_NAME));
    groups.add(fieldName(variableName, AdjustedFee.FIELD_REGISTRATION,
        AbstractIdentifiableCodable.FIELD_CODE));
    /*
     * On ajoute les autres au besoin
     */
  }
}
