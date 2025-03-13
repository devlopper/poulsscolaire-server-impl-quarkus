package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.Month;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingStatus;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente un financement.
 *
 * @author Christian
 *
 */
@Getter
@Setter
@Entity(name = Funding.ENTITY_NAME)
@Table(name = Funding.TABLE_NAME,
    uniqueConstraints = {@UniqueConstraint(columnNames = {Funding.COLUMN_BUDGET,
        Funding.COLUMN_DEPARTMENT_IDENTIFIER, Funding.COLUMN_MONTH,
        Funding.COLUMN_ACCOUNTING_ACCOUNT, Funding.COLUMN_SOURCE})})
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class Funding extends AbstractIdentifiableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_BUDGET, nullable = false)
  public Budget budget;

  @NotNull
  @Column(name = COLUMN_MONTH, nullable = false)
  public Month month;
  
  @NotNull
  @Column(name = COLUMN_DEPARTMENT_IDENTIFIER, nullable = false)
  public String departmentIdentifier;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_ACCOUNTING_ACCOUNT, nullable = false)
  public AccountingAccount accountingAccount;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_SOURCE, nullable = false)
  public FundingSource source;

  @NotNull
  @Column(name = COLUMN_AMOUNT, nullable = false)
  public Long amount;

  @Column(name = COLUMN_JUSTIFICATION)
  public String justification;

  @NotNull
  @Column(name = COLUMN_STATUS, nullable = false)
  public FundingStatus status;
  
  @Column(name = COLUMN_STATUS_REASON)
  public String statusReason;
  
  @Column(name = COLUMN_INPUT_DEADLINE)
  public LocalDateTime inputDeadline;
  
  @Transient
  public String statusAsString;
  
  @Transient
  public String budgetIdentifier;

  @Transient
  public String budgetAsString;

  @Transient
  public String monthAsString;
  
  @Transient
  public String departmentAsString;

  @Transient
  public String accountingAccountIdentifier;

  @Transient
  public String accountingAccountAsString;
  
  @Transient
  public String sourceIdentifier;

  @Transient
  public String sourceAsString;

  @Transient
  public String amountAsString;
  
  @Transient
  public Boolean amountInputable;

  /**
   * Transmissible.
   */
  @Transient
  public Boolean transmitable;

  /**
   * Acceptable.
   */
  @Transient
  public Boolean acceptable;

  /**
   * Retournable.
   */
  @Transient
  public Boolean returnable;

  /**
   * Approuvable.
   */
  @Transient
  public Boolean approvable;
  
  public static final String FIELD_BUDGET = "budget";
  public static final String FIELD_BUDGET_IDENTIFIER = "budgetIdentifier";
  public static final String FIELD_BUDGET_AS_STRING = "budgetAsString";

  public static final String FIELD_DEPARTMENT_IDENTIFIER = "departmentIdentifier";
  public static final String FIELD_DEPARTMENT_AS_STRING = "departmentAsString";

  public static final String FIELD_ACCOUNTING_ACCOUNT = "accountingAccount";
  public static final String FIELD_ACCOUNTING_ACCOUNT_IDENTIFIER = "accountingAccountIdentifier";
  public static final String FIELD_ACCOUNTING_ACCOUNT_AS_STRING = "accountingAccountAsString";

  public static final String FIELD_SOURCE = "source";
  public static final String FIELD_SOURCE_IDENTIFIER = "sourceIdentifier";
  public static final String FIELD_SOURCE_AS_STRING = "sourceAsString";

  public static final String FIELD_MONTH = "month";
  public static final String FIELD_MONTH_AS_STRING = "monthAsString";

  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";
  public static final String FIELD_AMOUNT_INPUTABLE = "amountInputable";
  
  public static final String FIELD_JUSTIFICATION = "justification";

  public static final String FIELD_STATUS = "status";
  public static final String FIELD_STATUS_AS_STRING = "statusAsString";
  public static final String FIELD_TRANSMITABLE = "transmitable";
  public static final String FIELD_ACCEPTABLE = "acceptable";
  public static final String FIELD_RETURNABLE = "returnable";
  public static final String FIELD_APPROVABLE = "approvable";
  public static final String FIELD_STATUS_REASON = "statusReason";
  
  public static final String ENTITY_NAME = "Funding";
  public static final String TABLE_NAME = "TA_FINANCEMENT";

  public static final String COLUMN_BUDGET = "BUDGET";
  public static final String COLUMN_DEPARTMENT_IDENTIFIER = "DEPARTEMENT";
  public static final String COLUMN_ACCOUNTING_ACCOUNT = "COMPTE_COMPTABLE";
  public static final String COLUMN_SOURCE = "SOURCE_FINANCEMENT";
  public static final String COLUMN_MONTH = "MOIS";
  public static final String COLUMN_AMOUNT = "MONTANT";
  public static final String COLUMN_JUSTIFICATION = "JUSTIFICATION";
  public static final String COLUMN_STATUS = "STATUT";
  public static final String COLUMN_STATUS_REASON = "MOTIF";
  public static final String COLUMN_INPUT_DEADLINE = "DATE_LIMITE_SAISIE";
}
