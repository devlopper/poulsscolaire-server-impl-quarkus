package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente un paiement de frais ajusté.
 *
 * @author Christian
 *
 */
@Entity(name = PaymentAdjustedFee.ENTITY_NAME)
@Table(name = PaymentAdjustedFee.TABLE_NAME)
@Audited
@AuditOverrides(
    value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
        @AuditOverride(forClass = AbstractIdentifiable.class)})
public class PaymentAdjustedFee extends AbstractIdentifiableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_PAYMENT, nullable = false)
  public Payment payment;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_ADJUSTED_FEE, nullable = false)
  public AdjustedFee adjustedFee;

  @NotNull
  @Column(name = COLUMN_AMOUNT, nullable = false)
  public int amount;

  @Transient
  public String paymentAsString;

  @Transient
  public String adjustedFeeAsString;

  @Transient
  public String amountAsString;

  public static final String FIELD_PAYMENT = "payment";
  public static final String FIELD_PAYMENT_AS_STRING = "paymentAsString";
  public static final String FIELD_ADJUSTED_FEE = "adjustedFee";
  public static final String FIELD_ADJUSTED_FEE_AS_STRING = "adjustedFeeAsString";
  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";

  public static final String ENTITY_NAME = "PaymentAdjustedFee";
  public static final String TABLE_NAME = "TA_PAIEMENT_FRAIS_AJUSTE";

  public static final String COLUMN_PAYMENT = "PAIEMENT";
  public static final String COLUMN_ADJUSTED_FEE = "FRAIS_AJUSTE";
  public static final String COLUMN_AMOUNT = "MONTANT";
}
