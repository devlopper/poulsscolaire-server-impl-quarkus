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
import java.time.Month;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente une ligne de budget.
 *
 * @author Christian
 *
 */
@Getter
@Setter
@Entity(name = BudgetLine.ENTITY_NAME)
@Table(name = BudgetLine.TABLE_NAME,
    uniqueConstraints = {@UniqueConstraint(columnNames = {BudgetLine.COLUMN_BUDGET,
        BudgetLine.COLUMN_DEPARTMENT_IDENTIFIER, BudgetLine.COLUMN_MONTH,
        BudgetLine.COLUMN_ACCOUNTING_ACCOUNT, BudgetLine.COLUMN_FUNDING_SOURCE})})
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class BudgetLine extends AbstractIdentifiableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_BUDGET, nullable = false)
  public Budget budget;

  @NotNull
  @Column(name = COLUMN_DEPARTMENT_IDENTIFIER, nullable = false)
  public String departmentIdentifier;

  @NotNull
  @Column(name = COLUMN_MONTH, nullable = false)
  public Month month;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_ACCOUNTING_ACCOUNT, nullable = false)
  public AccountingAccount accountingAccount;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_FUNDING_SOURCE, nullable = false)
  public FundingSource fundingSource;

  @NotNull
  @Column(name = COLUMN_AMOUNT, nullable = false)
  public Long amount;

  @Column(name = COLUMN_JUSTIFICATION)
  public String justification;

  @Transient
  public String budgetIdentifier;

  @Transient
  public String budgetAsString;

  @Transient
  public String departmentAsString;

  @Transient
  public String accountingAccountIdentifier;

  @Transient
  public String accountingAccountAsString;

  @Transient
  public String monthAsString;

  @Transient
  public String amountAsString;

  public static final String FIELD_BUDGET = "budget";
  public static final String FIELD_BUDGET_IDENTIFIER = "budgetIdentifier";
  public static final String FIELD_BUDGET_AS_STRING = "budgetAsString";

  public static final String FIELD_DEPARTMENT_IDENTIFIER = "budgetIdentifier";
  public static final String FIELD_DEPARTMENT_AS_STRING = "budgetAsString";

  public static final String FIELD_ACCOUNTING_ACCOUNT = "accountingAccount";
  public static final String FIELD_ACCOUNTING_ACCOUNT_IDENTIFIER = "accountingAccountIdentifier";
  public static final String FIELD_ACCOUNTING_ACCOUNT_AS_STRING = "accountingAccountAsString";

  public static final String FIELD_FUNDING_SOURCE = "fundingSource";
  public static final String FIELD_FUNDING_SOURCE_IDENTIFIER = "fundingSourceIdentifier";
  public static final String FIELD_FUNDING_SOURCE_AS_STRING = "fundingSourceAsString";

  public static final String FIELD_MONTH = "month";
  public static final String FIELD_MONTH_AS_STRING = "monthAsString";

  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";

  public static final String FIELD_JUSTIFICATION = "justification";

  public static final String ENTITY_NAME = "BudgetLine";
  public static final String TABLE_NAME = "TA_LIGNE_BUDGET";

  public static final String COLUMN_BUDGET = "BUDGET";
  public static final String COLUMN_DEPARTMENT_IDENTIFIER = "DEPARTEMENT";
  public static final String COLUMN_ACCOUNTING_ACCOUNT = "COMPTE_COMPTABLE";
  public static final String COLUMN_FUNDING_SOURCE = "SOURCE_FINANCEMENT";
  public static final String COLUMN_MONTH = "MOIS";
  public static final String COLUMN_AMOUNT = "MONTANT";
  public static final String COLUMN_JUSTIFICATION = "JUSTIFICATION";
}
