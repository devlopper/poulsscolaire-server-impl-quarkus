package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.Constant;
import ci.gouv.dgbf.extension.core.Core;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeDto;

/**
 * Cette classe représente la requête dynamique de {@link PaymentAdjustedFee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentAdjustedFeeDynamicQuery extends AbstractDynamicQuery<PaymentAdjustedFee> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public PaymentAdjustedFeeDynamicQuery() {
    super(PaymentAdjustedFee.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(PaymentAdjustedFeeDto.JSON_PAYMENT_AS_STRING)
        .nameFieldName(PaymentAdjustedFee.FIELD_PAYMENT_AS_STRING)
        .fieldName(
            fieldName(PaymentAdjustedFee.FIELD_PAYMENT, AbstractIdentifiableCodable.FIELD_CODE))
        .build();

    projectionBuilder().name(PaymentAdjustedFeeDto.JSON_ADJUSTED_FEE_AS_STRING)
        .nameFieldName(PaymentAdjustedFee.FIELD_ADJUSTED_FEE_AS_STRING)
        .fieldName(fieldName(PaymentAdjustedFee.FIELD_ADJUSTED_FEE, AdjustedFee.FIELD_FEE,
            Fee.FIELD_CATEGORY, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(PaymentAdjustedFeeDto.JSON_AMOUNT_AS_STRING)
        .nameFieldName(PaymentAdjustedFee.FIELD_AMOUNT_AS_STRING)
        .fieldName(PaymentAdjustedFee.FIELD_AMOUNT).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut

    orderBuilder()
        .fieldName(
            fieldName(PaymentAdjustedFee.FIELD_PAYMENT, AbstractIdentifiableCodable.FIELD_CODE))
        .build();
    orderBuilder().fieldName(fieldName(PaymentAdjustedFee.FIELD_ADJUSTED_FEE, AdjustedFee.FIELD_FEE,
        Fee.FIELD_CATEGORY, AbstractIdentifiableCodable.FIELD_CODE)).build();
  }

  /**
   * Cette méthode permet de formatter la sous-requête de sommation de montant.
   *
   * @param fieldName nom du champ
   * @param parentFieldName com du champ parent
   * @return sous-requête
   */
  public String formatSumAmountSubQuery(String fieldName, String parentFieldName,
      List<String> filters) {
    List<String> predicates = new ArrayList<>();
    predicates.add(String.format("sqt.%s = t%s", fieldName,
        Optional.ofNullable(parentFieldName).map(f -> "." + parentFieldName)
        .orElse(Constant.EMPTY_STRING)));
    Core.runIfNotNull(filters, () -> predicates.addAll(filters)); 
    String where = "WHERE " + predicates.stream().collect(Collectors.joining(" AND "));
    return formatValueOrZeroIfNull(formatSubQuery("SELECT " + formatSum("sqt.amount")
        + String.format(" FROM PaymentAdjustedFee sqt %s", where)));
  }
  
  public String formatSumAmountSubQuery(String fieldName, String parentFieldName) {
    return formatSumAmountSubQuery(fieldName, parentFieldName, null);
  }

  public String formatSumAmountSubQuery(String fieldName) {
    return formatSumAmountSubQuery(fieldName, null, null);
  }
}
