package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

/**
 * Cette classe représente la base de conteneur de montant.
 *
 * @author Christian
 *
 */
@MappedSuperclass
public abstract class AbstractAmountContainer extends AbstractIdentifiableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_AMOUNT, nullable = false)
  public Amount amount;

  /* valeurs dérivées */

  @Transient
  public String amountAsString;

  @Transient
  public int amountValue;

  @Transient
  public String amountValueAsString;

  @Transient
  public Integer amountRegistrationValuePart;

  @Transient
  public String amountRegistrationValuePartAsString;

  @Transient
  public Boolean amountOptional;

  @Transient
  public String amountOptionalAsString;

  @Transient
  public Integer amountPaymentOrderNumber;

  @Transient
  public String amountPaymentOrderNumberAsString;

  @Transient
  public Boolean amountRenewable;

  @Transient
  public String amountRenewableAsString;

  @Transient
  public String amountDeadlineIdentifier;

  @Transient
  public String amountDeadlineAsString;
  
  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";
  public static final String FIELD_AMOUNT_VALUE_AS_STRING = "amountValueAsString";
  public static final String FIELD_AMOUNT_VALUE = "amountValue";
  public static final String FIELD_AMOUNT_REGISTRATION_VALUE_PART = "amountRegistrationValuePart";
  public static final String FIELD_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING =
      "amountRegistrationValuePartAsString";
  public static final String FIELD_AMOUNT_OPTIONAL = "amountOptional";
  public static final String FIELD_AMOUNT_OPTIONAL_AS_STRING = "amountOptionalAsString";
  public static final String FIELD_AMOUNT_PAYMENT_ORDER_NUMBER = "amountPaymentOrderNumber";
  public static final String FIELD_AMOUNT_PAYMENT_ORDER_NUMBER_AS_STRING =
      "amountPaymentOrderNumberAsString";
  public static final String FIELD_AMOUNT_RENEWABLE = "amountRenewable";
  public static final String FIELD_AMOUNT_RENEWABLE_AS_STRING = "amountRenewableAsString";
  public static final String FIELD_AMOUNT_DEADLINE_IDENTIFIER = "amountDeadlineIdentifier";
  public static final String FIELD_AMOUNT_DEADLINE_AS_STRING = "amountDeadlineAsString";

  public static final String COLUMN_AMOUNT = "MONTANT";
}
