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
import org.cyk.system.poulsscolaire.server.api.fee.AbstractAmountContainerDto;

/**
 * Cette classe représente la requête dynamique de {@link Fee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeDynamicQuery extends AbstractDynamicQuery<Fee> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public FeeDynamicQuery() {
    super(Fee.class);
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

    /*
    projectionBuilder().name(AbstractAmountContainerDto.JSON_REGISTRATION_VALUE_PART)
        .fieldName(Fee.FIELD_REGISTRATION_VALUE_PART).build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_REGISTRATION_VALUE_PART_AS_STRING)
        .nameFieldName(Fee.FIELD_REGISTRATION_VALUE_PART_AS_STRING)
        .fieldName(Fee.FIELD_REGISTRATION_VALUE_PART).build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_PAYMENT_ORDER_NUMBER)
        .fieldName(Fee.FIELD_PAYMENT_ORDER_NUMBER).build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_PAYMENT_ORDER_NUMBER_AS_STRING)
        .nameFieldName(Fee.FIELD_PAYMENT_ORDER_NUMBER_AS_STRING)
        .fieldName(Fee.FIELD_PAYMENT_ORDER_NUMBER).build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_OPTIONAL)
    .fieldName(Fee.FIELD_OPTIONAL).build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_OPTIONAL_AS_STRING)
        .nameFieldName(Fee.FIELD_OPTIONAL_AS_STRING).fieldName(Fee.FIELD_OPTIONAL).build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_RENEWABLE)
    .fieldName(Fee.FIELD_RENEWABLE)
        .build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_RENEWABLE_AS_STRING)
        .nameFieldName(Fee.FIELD_RENEWABLE_AS_STRING).fieldName(Fee.FIELD_RENEWABLE).build();

    projectionBuilder().name(AbstractAmountContainerDto.JSON_DEADLINE_IDENTIFIER)
        .nameFieldName(Fee.FIELD_DEADLINE_IDENTIFIER)
        .fieldName(fieldName(Fee.FIELD_DEADLINE, AbstractIdentifiable.FIELD_IDENTIFIER)).build();
    projectionBuilder().name(AbstractAmountContainerDto.JSON_DEADLINE_AS_STRING)
        .nameFieldName(Fee.FIELD_DEADLINE_AS_STRING)
        .fieldName(fieldName(Fee.FIELD_DEADLINE, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();
    */
    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    //orderBuilder().fieldName(fieldName(Fee.FIELD_DEADLINE, Deadline.FIELD_DATE)).ascending(false)
    //    .build();
  }
}
