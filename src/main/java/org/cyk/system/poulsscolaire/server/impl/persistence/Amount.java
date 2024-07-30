package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import org.cyk.system.poulsscolaire.server.api.fee.AmountService;

/**
 * Cette classe représente un montant.
 *
 * @author Christian
 *
 */
@Entity(name = Amount.ENTITY_NAME)
@Table(name = Amount.TABLE_NAME)
public class Amount extends AbstractIdentifiableAuditable {

  @NotNull
  @Column(name = COLUMN_VALUE, nullable = false)
  public int value;

  @Column(name = COLUMN_REGISTRATION_VALUE_PART)
  public Integer registrationValuePart;

  @NotNull
  @Column(name = COLUMN_OPTIONAL, nullable = false)
  public Boolean optional;

  @Column(name = COLUMN_PAYMENT_ORDER_NUMBER)
  public Integer paymentOrderNumber;

  @NotNull
  @Column(name = COLUMN_RENEWABLE, nullable = false)
  public Boolean renewable;

  /* valeurs dérivées */

  @Transient
  public String valueAsString;

  @Transient
  public Boolean valuePayableEqualsZero;

  @Transient
  public String registrationValuePartAsString;

  @Transient
  public String optionalAsString;

  @Transient
  public String paymentOrderNumberAsString;

  @Transient
  public String renewableAsString;

  @Transient
  public String deadlineIdentifier;

  @Transient
  public String deadlineAsString;

  @Transient
  public Boolean deadlineDateOver;

  /**
   * Cette méthode permet d'assigner les attributs.
   *
   * @param request requête
   * @param array tableau
   */
  public void set(AmountService.AmountSaveData request, Object[] array) {
    value = request.getValue();
    registrationValuePart = Optional.ofNullable(request.getRegistrationValuePart()).orElse(0);
    optional = request.getOptional();
    if (Optional.ofNullable(optional).orElse(true)) {
      paymentOrderNumber = null;
    } else {
      paymentOrderNumber = request.getPaymentOrderNumber();
    }
    renewable = request.getRenewable();
  }

  public static final String FIELD_VALUE = "value";
  public static final String FIELD_VALUE_AS_STRING = "valueAsString";
  public static final String FIELD_VALUE_PAYABLE_EQUALS_ZERO = "valuePayableEqualsZero";
  public static final String FIELD_REGISTRATION_VALUE_PART = "registrationValuePart";
  public static final String FIELD_REGISTRATION_VALUE_PART_AS_STRING =
      "registrationValuePartAsString";
  public static final String FIELD_OPTIONAL = "optional";
  public static final String FIELD_OPTIONAL_AS_STRING = "optionalAsString";
  public static final String FIELD_PAYMENT_ORDER_NUMBER = "paymentOrderNumber";
  public static final String FIELD_PAYMENT_ORDER_NUMBER_AS_STRING = "paymentOrderNumberAsString";
  public static final String FIELD_RENEWABLE = "renewable";
  public static final String FIELD_RENEWABLE_AS_STRING = "renewableAsString";
  public static final String FIELD_DEADLINE = "deadline";
  public static final String FIELD_DEADLINE_IDENTIFIER = "deadlineIdentifier";
  public static final String FIELD_DEADLINE_AS_STRING = "deadlineAsString";
  public static final String FIELD_DEADLINE_DATE_OVER = "deadlineDateOver";

  public static final String ENTITY_NAME = "Amount";
  public static final String TABLE_NAME = "TA_MONTANT";

  public static final String COLUMN_VALUE = "VALEUR";
  public static final String COLUMN_REGISTRATION_VALUE_PART = "VALEUR_INSCRIPTION";
  public static final String COLUMN_OPTIONAL = "FACULTATIF";
  public static final String COLUMN_PAYMENT_ORDER_NUMBER = "NUMERO_ORDRE_PAIEMENT";
  public static final String COLUMN_RENEWABLE = "RECONDUCTIBLE";
  public static final String COLUMN_DEADLINE = "ECHEANCE";  
}
