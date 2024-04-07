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
import org.cyk.system.poulsscolaire.server.api.AmountDto;

/**
 * Cette classe représente la requête dynamique de {@link Amount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountDynamicQuery extends AbstractDynamicQuery<Amount> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public AmountDynamicQuery() {
    super(Amount.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AmountDto.JSON_VALUE).fieldName(Amount.FIELD_VALUE).build();
    projectionBuilder().name(AmountDto.JSON_VALUE_AS_STRING)
        .nameFieldName(Amount.FIELD_VALUE_AS_STRING).fieldName(Amount.FIELD_VALUE).build();


    projectionBuilder().name(AmountDto.JSON_REGISTRATION_VALUE_PART)
        .fieldName(Amount.FIELD_REGISTRATION_VALUE_PART).build();
    projectionBuilder().name(AmountDto.JSON_REGISTRATION_VALUE_PART_AS_STRING)
        .nameFieldName(Amount.FIELD_REGISTRATION_VALUE_PART_AS_STRING)
        .fieldName(Amount.FIELD_REGISTRATION_VALUE_PART).build();

    projectionBuilder().name(AmountDto.JSON_PAYMENT_ORDER_NUMBER)
        .fieldName(Amount.FIELD_PAYMENT_ORDER_NUMBER).build();
    projectionBuilder().name(AmountDto.JSON_PAYMENT_ORDER_NUMBER_AS_STRING)
        .nameFieldName(Amount.FIELD_PAYMENT_ORDER_NUMBER_AS_STRING)
        .fieldName(Amount.FIELD_PAYMENT_ORDER_NUMBER).build();

    projectionBuilder().name(AmountDto.JSON_OPTIONAL).fieldName(Amount.FIELD_OPTIONAL).build();
    projectionBuilder().name(AmountDto.JSON_OPTIONAL_AS_STRING)
        .nameFieldName(Amount.FIELD_OPTIONAL_AS_STRING).fieldName(Amount.FIELD_OPTIONAL).build();

    projectionBuilder().name(AmountDto.JSON_RENEWABLE).fieldName(Amount.FIELD_RENEWABLE)
        .build();
    projectionBuilder().name(AmountDto.JSON_RENEWABLE_AS_STRING)
        .nameFieldName(Amount.FIELD_RENEWABLE_AS_STRING).fieldName(Amount.FIELD_RENEWABLE).build();

    projectionBuilder().name(AmountDto.JSON_DEADLINE_IDENTIFIER)
        .nameFieldName(Amount.FIELD_DEADLINE_IDENTIFIER)
        .fieldName(fieldName(Amount.FIELD_DEADLINE, AbstractIdentifiable.FIELD_IDENTIFIER)).build();
    projectionBuilder().name(AmountDto.JSON_DEADLINE_AS_STRING)
        .nameFieldName(Amount.FIELD_DEADLINE_AS_STRING)
        .fieldName(fieldName(Amount.FIELD_DEADLINE, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(fieldName(Amount.FIELD_DEADLINE, Deadline.FIELD_DATE)).ascending(false)
        .build();
  }
}
