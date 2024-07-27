package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import ci.gouv.dgbf.extension.server.service.api.request.ProjectionDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Set;
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
  String paymentAdjustedFeeVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public PaymentDynamicQuery() {
    super(Payment.class);
    paymentAdjustedFeeVariableName = "paf";
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
        .tupleVariableName(paymentAdjustedFeeVariableName)
        .expression(String.format("COALESCE(SUM(%s.%s), 0)", paymentAdjustedFeeVariableName,
            PaymentAdjustedFee.FIELD_AMOUNT))
        .resultConsumer((i, a) -> i.amountAsString = a.getNextAsLongFormatted()).build();

    // Jointures
    joinBuilder().projectionsNames(PaymentDto.JSON_AMOUNT, PaymentDto.JSON_AMOUNT_AS_STRING)
        .with(PaymentAdjustedFee.class).tupleVariableName(paymentAdjustedFeeVariableName)
        .fieldName(PaymentAdjustedFee.FIELD_PAYMENT).parentFieldName(null).leftInnerOrRight(true)
        .build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(PaymentFilter.JSON_REGISTRATION_IDENTIFIER)
        .fieldName(fieldName(Payment.FIELD_REGISTRATION, AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(PaymentFilter::getRegistrationIdentifier).build();

    predicateBuilder().name(PaymentFilter.JSON_CANCELED).fieldName(Payment.FIELD_CANCELED)
        .valueFunction(PaymentFilter::getCanceled).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }

  @Override
  protected boolean isGrouped(DynamicQueryParameters<Payment> parameters) {
    return ProjectionDto.hasOneOfNames(parameters.getProjection(), PaymentDto.JSON_AMOUNT,
        PaymentDto.JSON_AMOUNT_AS_STRING);
  }

  @Override
  protected void buildGroups(DynamicQueryParameters<Payment> parameters, Set<String> groups) {
    /*
     * On ajoute l'identifiant pour l'apsect technique de regroupement
     */
    groups.add(fieldName(variableName, AbstractIdentifiable.FIELD_IDENTIFIER));
    /*
     * On ajoute les éléments d'ordre
     */
    groups.add(fieldName(variableName, AbstractIdentifiableCodable.FIELD_CODE));
    /*
     * On ajoute les autres au besoin
     */
  }
}
