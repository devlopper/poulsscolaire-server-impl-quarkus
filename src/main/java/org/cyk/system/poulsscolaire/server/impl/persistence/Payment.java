package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente un paiement.
 *
 * @author Christian
 *
 */
@Entity(name = Payment.ENTITY_NAME)
@Table(name = Payment.TABLE_NAME)
@Audited
@AuditOverrides(
    value = {@AuditOverride(forClass = AbstractIdentifiableCodableAuditable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodable.class),
        @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class Payment extends AbstractIdentifiableCodableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_REGISTRATION, nullable = false)
  public Registration registration;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_MODE, nullable = false)
  public PaymentMode mode;
  
  //@NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_ACCOUNTING_OPERATION/*, nullable = false*/)
  public AccountingOperation accountingOperation;

  @NotNull
  @Column(name = COLUMN_CANCELED, nullable = false)
  public Boolean canceled;
  
  @Column(name = COLUMN_INITIATOR)
  public String initiator;
  
  /* Transients */

  @Transient
  public String registrationAsString;
  
  @Transient
  public int amount;

  @Transient
  public String modeAsString;

  @Transient
  public String amountAsString;

  @Transient
  public String dateAsString;

  @Transient
  public List<Object[]> payables;

  @Transient
  public String creationDateAsString;
  
  @Transient
  public String creationActor;
  
  @Transient
  public String auditCancellationAsString;
  
  @Transient
  public String branchInstanceAsString;
  
  @Transient
  public String accountingOperationAsString;
  
  public static final String FIELD_REGISTRATION = "registration";
  public static final String FIELD_REGISTRATION_AS_STRING = "registrationAsString";
  public static final String FIELD_MODE = "mode";
  public static final String FIELD_MODE_AS_STRING = "modeAsString";
  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";
  public static final String FIELD_DATE_AS_STRING = "dateAsString";
  public static final String FIELD_CANCELED = "canceled";
  public static final String FIELD_INITIATOR = "initiator";
  public static final String FIELD_AUDIT_CANCELLATION_AS_STRING = "auditCancellationAsString";
  public static final String FIELD_BRANCH_INSTANCE_AS_STRING = "branchInstanceAsString";
  public static final String FIELD_ACCOUNTING_OPERATION_AS_STRING = "accountingOperationAsString";
  
  public static final String ENTITY_NAME = "Payment";
  public static final String TABLE_NAME = "TA_PAIEMENT";

  public static final String COLUMN_REGISTRATION = "INSCRIPTION";
  public static final String COLUMN_MODE = "MODE";
  public static final String COLUMN_ACCOUNTING_OPERATION = "OPERATION_COMPTABLE";
  public static final String COLUMN_CANCELED = "ANNULE";
  public static final String COLUMN_INITIATOR = "INITIATEUR";
}
