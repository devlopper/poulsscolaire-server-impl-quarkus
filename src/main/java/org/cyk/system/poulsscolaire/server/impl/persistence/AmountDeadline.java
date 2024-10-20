package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente une échéance de montant.
 *
 * @author Christian
 *
 */
@Entity(name = AmountDeadline.ENTITY_NAME)
@Table(name = AmountDeadline.TABLE_NAME)
@NamedQueries(value = {
    @NamedQuery(name = AmountDeadline.QUERY_READ_BY_FEES_IDENTIFIER,
        query = AmountDeadline.QUERY_READ_BY_FEES_VALUE),
    @NamedQuery(name = AmountDeadline.QUERY_SUM_PAYMENT_BY_AMOUNT_IDENTIFIER,
        query = AmountDeadline.QUERY_SUM_PAYMENT_BY_AMOUNT_VALUE),
    @NamedQuery(name = AmountDeadline.QUERY_READ_WHERE_PAYMENT_NOT_ZERO_BY_REGISTRATION_IDENTIFIER,
        query = AmountDeadline.QUERY_READ_WHERE_PAYMENT_NOT_ZERO_BY_REGISTRATION_VALUE)})
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
public class AmountDeadline extends AbstractIdentifiableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_AMOUNT, nullable = false)
  public Amount amount;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_DEADLINE, nullable = false)
  public Deadline deadline;

  @NotNull
  @Column(name = COLUMN_PAYMENT, nullable = false)
  public Integer payment;

  /* valeurs dérivées */

  @Transient
  public String adjustedFeeIdentifier;

  @Transient
  public String adjustedFeeAsString;

  @Transient
  public String feeCategoryAsString;

  @Transient
  public String branchAsString;

  @Transient
  public String studentAsString;

  @Transient
  public String deadlineIdentifier;

  @Transient
  public String deadlineAsString;

  @Transient
  public Boolean deadlinePassed;

  @Transient
  public Boolean deadlineRunning;

  @Transient
  public String amountIdentifier;

  @Transient
  public String amountAsString;

  @Transient
  public String paymentAsString;

  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";
  public static final String FIELD_AMOUNT_IDENTIFIER = "amountIdentifier";
  public static final String FIELD_ADJUSTED_FEE = "adjustedFee";
  public static final String FIELD_ADJUSTED_FEE_AS_STRING = "adjustedFeeAsString";
  public static final String FIELD_ADJUSTED_FEE_IDENTIFIER = "adjustedFeeIdentifier";
  public static final String FIELD_DEADLINE = "deadline";
  public static final String FIELD_DEADLINE_IDENTIFIER = "deadlineIdentifier";
  public static final String FIELD_DEADLINE_AS_STRING = "deadlineAsString";
  public static final String FIELD_DEADLINE_PASSED = "deadlinePassed";
  public static final String FIELD_DEADLINE_RUNNING = "deadlineRunning";
  public static final String FIELD_PAYMENT = "payment";
  public static final String FIELD_PAYMENT_AS_STRING = "paymentAsString";

  public static final String ENTITY_NAME = "AmountDeadline";
  public static final String TABLE_NAME = "TA_ECHEANCE_MONTANT";

  public static final String COLUMN_AMOUNT = "MONTANT";
  public static final String COLUMN_DEADLINE = "ECHEANCE";
  public static final String COLUMN_PAYMENT = "PAIEMENT";

  public static final String QUERY_READ_BY_FEES_IDENTIFIER = "AmountDeadline.readByFees";
  public static final String QUERY_READ_BY_FEES_VALUE =
      "SELECT t, f FROM AmountDeadline t JOIN Fee f ON f.amount = t.amount";

  public static final String QUERY_SUM_PAYMENT_BY_AMOUNT_IDENTIFIER =
      "AmountDeadline.sumPaymentByAmount";
  public static final String QUERY_SUM_PAYMENT_BY_AMOUNT_VALUE =
      "SELECT SUM(t.payment) FROM AmountDeadline t JOIN Amount a ON a = t.amount WHERE t.amount = :"
          + FIELD_AMOUNT;

  public static final String QUERY_READ_WHERE_PAYMENT_NOT_ZERO_BY_REGISTRATION_IDENTIFIER =
      "AmountDeadline.readWherePaymentNotZeroByRegistration";
  public static final String QUERY_READ_WHERE_PAYMENT_NOT_ZERO_BY_REGISTRATION_VALUE =
      "SELECT t FROM AmountDeadline t JOIN AdjustedFee af ON af.amount = t.amount "
          + "WHERE af.registration = :registration AND t.payment <> 0";
}
