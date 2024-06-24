package org.cyk.system.poulsscolaire.server.impl.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

/**
 * Cette classe représente un frais ajusté.
 *
 * @author Christian
 *
 */
@Entity(name = AdjustedFee.ENTITY_NAME)
@Table(name = AdjustedFee.TABLE_NAME)
@NamedQueries(
    value = {@NamedQuery(name = AdjustedFee.QUERY_READ_FOR_PAYMENT_BY_REGISTRATION_IDENTIFIER,
        query = AdjustedFee.QUERY_READ_FOR_PAYMENT_BY_REGISTRATION_VALUE)})
public class AdjustedFee extends AbstractAmountContainer {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_FEE, nullable = false)
  public Fee fee;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_REGISTRATION, nullable = false)
  public Registration registration;

  /* valeurs dérivées */

  @Transient
  public String feeIdentifier;
  
  @Transient
  public Boolean feeOptional;
  
  @Transient
  public String feeAsString;

  @Transient
  public String registrationIdentifier;

  @Transient
  public String registrationAsString;
  
  @Transient
  public Long amountValuePaid;

  @Transient
  public String amountValuePaidAsString;
  
  @Transient
  public Long amountValuePayable;

  @Transient
  public String amountValuePayableAsString;
  
  public static final String FIELD_FEE = "fee";
  public static final String FIELD_FEE_IDENTIFIER = "feeIdentifier";
  public static final String FIELD_FEE_OPTIONAL = "feeOptional";
  public static final String FIELD_FEE_AS_STRING = "feeAsString";
  public static final String FIELD_REGISTRATION = "registration";
  public static final String FIELD_REGISTRATION_IDENTIFIER = "registrationIdentifier";
  public static final String FIELD_REGISTRATION_AS_STRING = "registrationAsString";
  public static final String FIELD_AMOUNT_VALUE_PAID_AS_STRING = "amountValuePaidAsString";
  public static final String FIELD_AMOUNT_VALUE_PAID = "amountValuePaid";
  public static final String FIELD_AMOUNT_VALUE_PAYABLE = "amountValuePayable";
  public static final String FIELD_AMOUNT_VALUE_PAYABLE_AS_STRING =
      "amountValuePayableAsString";
  
  public static final String ENTITY_NAME = "AdjustedFee";
  public static final String TABLE_NAME = "TA_FRAIS_AJUSTE";

  public static final String COLUMN_FEE = "FRAIS";
  public static final String COLUMN_REGISTRATION = "INSCRIPTION";

  public static final String QUERY_READ_FOR_PAYMENT_BY_REGISTRATION_IDENTIFIER =
      "AdjustedFee.readForPaymentByRegistration";
  public static final String QUERY_READ_FOR_PAYMENT_BY_REGISTRATION_VALUE =
      "SELECT t,SUM(COALESCE(p.amount,0)),t.amount.value - SUM(COALESCE(p.amount,0))"
          + " FROM AdjustedFee t " + "LEFT JOIN PaymentAdjustedFee p ON p."
          + PaymentAdjustedFee.FIELD_ADJUSTED_FEE + " = t " + "WHERE t." + FIELD_REGISTRATION
          + " = :" + FIELD_REGISTRATION
          + " AND (t.amount.optional IS NULL OR t.amount.optional = false)"
          + " AND t.amount.value > 0" + " GROUP BY t" + " ORDER BY t.amount.paymentOrderNumber ASC";
}
