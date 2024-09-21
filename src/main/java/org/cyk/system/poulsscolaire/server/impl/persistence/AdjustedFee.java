package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

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
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class),
    @AuditOverride(forClass = AbstractAmountContainer.class)})
@EqualsAndHashCode(callSuper = true)
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
  public String feeCategoryAsString;

  @Transient
  public String registrationIdentifier;

  @Transient
  public String registrationAsString;

  @Transient
  public String registrationSchoolingSchoolAsString;

  @Transient
  public String registrationSchoolingBranchAsString;

  @Transient
  public String registrationSchoolingPeriodAsString;

  @Transient
  public String registrationSeniorityAsString;

  @Transient
  public String registrationAssignmentTypeAsString;

  @Transient
  public String registrationStudentAsString;

  @Transient
  public String initialAmountToPayAsString;

  @Transient
  public String initialRegistrationAmountToPayAsString;

  @Transient
  public String reducedAmountAsString;

  @Transient
  public String reducedRegistrationAmountAsString;

  @Transient
  public Long amountValueToPay;

  @Transient
  public String amountValueToPayAsString;

  @Transient
  public Long amountValuePaid;

  @Transient
  public String amountValuePaidAsString;

  @Transient
  public Long amountValuePayable;

  @Transient
  public String amountValuePayableAsString;

  @Transient
  public String expectedPaymentAsString;

  @Transient
  public Boolean latePayment;

  @Transient
  public Boolean reducedAmountIsZero;

  @Transient
  public Boolean reducedRegistrationAmountIsZero;

  @Transient
  public String branchInstanceAsString;

  public static final String FIELD_FEE = "fee";
  public static final String FIELD_FEE_IDENTIFIER = "feeIdentifier";
  public static final String FIELD_FEE_OPTIONAL = "feeOptional";
  public static final String FIELD_FEE_AS_STRING = "feeAsString";
  public static final String FIELD_FEE_CATEGORY_AS_STRING = "feeCategoryAsString";
  public static final String FIELD_REGISTRATION = "registration";
  public static final String FIELD_REGISTRATION_IDENTIFIER = "registrationIdentifier";
  public static final String FIELD_REGISTRATION_AS_STRING = "registrationAsString";
  public static final String FIELD_INITIAL_AMOUNT_TO_PAY_AS_STRING = "initialAmountToPayAsString";
  public static final String FIELD_INITIAL_REGISTRATION_AMOUNT_TO_PAY_AS_STRING =
      "initialRegistrationAmountToPayAsString";
  public static final String FIELD_AMOUNT_VALUE_TO_PAY_AS_STRING = "amountValueToPayAsString";
  public static final String FIELD_AMOUNT_VALUE_TO_PAY = "amountValueToPay";
  public static final String FIELD_REDUCED_AMOUNT_AS_STRING = "reducedAmountAsString";
  public static final String FIELD_REDUCED_REGISTRATION_AMOUNT_AS_STRING =
      "reducedRegistrationAmountAsString";
  public static final String FIELD_AMOUNT_VALUE_PAID_AS_STRING = "amountValuePaidAsString";
  public static final String FIELD_AMOUNT_VALUE_PAID = "amountValuePaid";
  public static final String FIELD_AMOUNT_VALUE_PAYABLE = "amountValuePayable";
  public static final String FIELD_AMOUNT_VALUE_PAYABLE_AS_STRING = "amountValuePayableAsString";
  public static final String FIELD_EXPECTED_PAYMENT_AS_STRING = "expectedPaymentAsString";
  public static final String FIELD_LATE_PAYMENT = "latePayment";
  public static final String FIELD_REDUCED_AMOUNT_IS_ZERO = "reducedAmountIsZero";
  public static final String FIELD_REDUCED_REGISTRATION_AMOUNT_IS_ZERO =
      "reducedRegistrationAmountIsZero";
  public static final String FIELD_BRANCH_INSTANCE_AS_STRING = "branchInstanceAsString";

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
