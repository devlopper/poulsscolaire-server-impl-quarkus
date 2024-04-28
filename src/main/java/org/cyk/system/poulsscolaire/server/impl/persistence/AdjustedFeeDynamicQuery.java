package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AbstractAmountContainerDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeDto;

/**
 * Cette classe représente la requête dynamique de {@link AdjustedFee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeeDynamicQuery extends AbstractDynamicQuery<AdjustedFee> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public AdjustedFeeDynamicQuery() {
    super(AdjustedFee.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_VALUE)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_VALUE)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_VALUE)).build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_VALUE_AS_STRING)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_VALUE_AS_STRING)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_VALUE)).build();

    projectionBuilder().name(AdjustedFeeDto.JSON_FEE_AS_STRING)
        .nameFieldName(AdjustedFee.FIELD_FEE_AS_STRING).fieldName(fieldName(AdjustedFee.FIELD_FEE,
            Fee.FIELD_CATEGORY, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(AdjustedFeeDto.JSON_REGISTRATION_AS_STRING)
        .nameFieldName(AdjustedFee.FIELD_REGISTRATION_AS_STRING)
        .fieldName(
            fieldName(AdjustedFee.FIELD_REGISTRATION, AbstractIdentifiableCodable.FIELD_CODE))
        .build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_REGISTRATION_VALUE_PART)
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
        .fieldName(
            fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_PAYMENT_ORDER_NUMBER))
        .build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_PAYMENT_ORDER_NUMBER_AS_STRING)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_PAYMENT_ORDER_NUMBER_AS_STRING)
        .fieldName(
            fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_PAYMENT_ORDER_NUMBER))
        .build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_OPTIONAL)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_OPTIONAL)).build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_OPTIONAL_AS_STRING)
        .nameFieldName(AbstractAmountContainer.FIELD_AMOUNT_OPTIONAL_AS_STRING)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_OPTIONAL)).build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_AMOUNT_RENEWABLE)
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

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(fieldName(AdjustedFee.FIELD_FEE, Fee.FIELD_CATEGORY,
        AbstractIdentifiableCodableNamable.FIELD_NAME)).ascending(false).build();
    orderBuilder()
        .fieldName(
            fieldName(AdjustedFee.FIELD_REGISTRATION, AbstractIdentifiableCodable.FIELD_CODE))
        .ascending(false).build();
  }
}
