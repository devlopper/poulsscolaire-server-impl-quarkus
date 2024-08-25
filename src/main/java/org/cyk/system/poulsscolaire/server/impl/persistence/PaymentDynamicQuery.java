package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Action;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentFilter;

/**
 * Cette classe représente la requête dynamique de {@link Payment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentDynamicQuery extends AbstractDynamicQuery<Payment> {

  @Inject
  @Getter
  EntityManager entityManager;

  String schoolVariableName;
  String paymentAmountsVariableName;
  String paymentDatesVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public PaymentDynamicQuery() {
    super(Payment.class);
    schoolVariableName = "s";
    paymentAmountsVariableName = "pa";
    paymentDatesVariableName = "pd";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(PaymentDto.JSON_CANCELED).fieldName(Payment.FIELD_CANCELED).build();

    projectionBuilder().name(PaymentDto.JSON_MODE_AS_STRING)
        .nameFieldName(Payment.FIELD_MODE_AS_STRING)
        .fieldName(fieldName(Payment.FIELD_MODE, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(PaymentDto.JSON_AMOUNT_AS_STRING)
        .tupleVariableName(paymentAmountsVariableName).nameFieldName(Payment.FIELD_AMOUNT_AS_STRING)
        .fieldName(PaymentAmounts.FIELD_TOTAL).build();

    projectionBuilder().name(PaymentDto.JSON_CREATION_ACTOR)
        .tupleVariableName(paymentDatesVariableName).nameFieldName(Payment.FIELD_CREATION_ACTOR)
        .fieldName(fieldName(PaymentDates.FIELD_CREATION, Action.FIELD_WHO)).build();

    projectionBuilder().name(PaymentDto.JSON_CREATION_DATE_AS_STRING)
        .tupleVariableName(paymentDatesVariableName)
        .nameFieldName(Payment.FIELD_CREATION_DATE_AS_STRING)
        .fieldName(fieldName(PaymentDates.FIELD_CREATION, Action.FIELD_WHEN)).build();

    projectionBuilder().name(PaymentDto.JSON_CANCELLATION_ACTOR)
        .tupleVariableName(paymentDatesVariableName).nameFieldName(Payment.FIELD_CANCELLATION_ACTOR)
        .fieldName(fieldName(PaymentDates.FIELD_CANCELLATION, Action.FIELD_WHO)).build();

    projectionBuilder().name(PaymentDto.JSON_CANCELLATION_DATE_AS_STRING)
        .tupleVariableName(paymentDatesVariableName)
        .nameFieldName(Payment.FIELD_CANCELLATION_DATE_AS_STRING)
        .fieldName(fieldName(PaymentDates.FIELD_CANCELLATION, Action.FIELD_WHEN)).build();

    // Jointures
    joinBuilder().projectionsNames(PaymentDto.JSON_AMOUNT, PaymentDto.JSON_AMOUNT_AS_STRING)
        .with(PaymentAmounts.class).tupleVariableName(paymentAmountsVariableName)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .parentFieldName(AbstractIdentifiable.FIELD_IDENTIFIER).leftInnerOrRight(true).build();

    joinBuilder()
        .projectionsNames(PaymentDto.JSON_CREATION_ACTOR, PaymentDto.JSON_CREATION_DATE_AS_STRING,
            PaymentDto.JSON_CANCELLATION_ACTOR, PaymentDto.JSON_CANCELLATION_DATE_AS_STRING)
        .predicatesNames(PaymentFilter.JSON_FROM_DATE, PaymentFilter.JSON_TO_DATE)
        .with(PaymentDates.class).tupleVariableName(paymentDatesVariableName)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .parentFieldName(AbstractIdentifiable.FIELD_IDENTIFIER).leftInnerOrRight(true).build();

    joinBuilder().predicatesNames(PaymentDto.JSON_SCHOOL_IDENTIFIER).entityName(School.ENTITY_NAME)
        .tupleVariableName(schoolVariableName).parentFieldName(fieldName(Payment.FIELD_REGISTRATION,
            Registration.FIELD_SCHOOLING, Schooling.FIELD_SCHOOL_IDENTIFIER))
        .leftInnerOrRight(true).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(PaymentFilter.JSON_REGISTRATION_IDENTIFIER)
        .fieldName(fieldName(Payment.FIELD_REGISTRATION, AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(PaymentFilter::getRegistrationIdentifier).build();

    predicateBuilder().name(PaymentFilter.JSON_SCHOOL_IDENTIFIER)
        .fieldName(fieldName(Payment.FIELD_REGISTRATION, Registration.FIELD_SCHOOLING,
            Schooling.FIELD_SCHOOL_IDENTIFIER))
        .valueFunction(PaymentFilter::getSchoolIdentifier).build();

    predicateBuilder().name(PaymentFilter.JSON_CANCELED).fieldName(Payment.FIELD_CANCELED)
        .valueFunction(PaymentFilter::getCanceled).build();

    predicateBuilder().name(PaymentFilter.JSON_FROM_DATE)
        .expression("pd.creation.when >= :" + PaymentFilter.JSON_FROM_DATE)
        .valueFunction(PaymentFilter::getFromDate).build();

    predicateBuilder().name(PaymentFilter.JSON_TO_DATE)
        .expression("pd.creation.when <= :" + PaymentFilter.JSON_TO_DATE)
        .valueFunction(PaymentFilter::getToDate).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }
}
