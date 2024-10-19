package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountType;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente un compte comptable.
 *
 * @author Christian
 *
 */
@Entity(name = AccountingAccount.ENTITY_NAME)
@Table(name = AccountingAccount.TABLE_NAME,
    uniqueConstraints = {@UniqueConstraint(
        columnNames = {AccountingAccount.COLUMN_PLAN, AbstractIdentifiableCodable.COLUMN_CODE})})
@AttributeOverrides(value = {@AttributeOverride(name = AbstractIdentifiableCodable.FIELD_CODE,
    column = @Column(name = AbstractIdentifiableCodable.COLUMN_CODE, unique = false))})
@Audited
@AuditOverrides(
    value = {@AuditOverride(forClass = AbstractIdentifiableCodableNamableAuditable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodableNamable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodable.class),
        @AuditOverride(forClass = AbstractIdentifiable.class)})
@NamedQueries(value = {
    @NamedQuery(name = AccountingAccount.QUERY_READ_PAYMENT_BY_SCHOOL_IDENTIFIER_IDENTIFIER,
        query = AccountingAccount.QUERY_READ_PAYMENT_BY_SCHOOL_IDENTIFIER_VALUE)})
@EqualsAndHashCode(callSuper = true)
public class AccountingAccount extends AbstractIdentifiableCodableNamableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_PLAN, nullable = false)
  public AccountingPlan plan;

  @NotNull
  @Column(name = COLUMN_TYPE, nullable = false)
  public AccountingAccountType type;

  @Transient
  public String planIdentifier;

  @Transient
  public String planAsString;

  @Transient
  public String typeAsString;

  public static final String FIELD_PLAN = "plan";
  public static final String FIELD_PLAN_IDENTIFIER = "planIdentifier";
  public static final String FIELD_PLAN_AS_STRING = "planAsString";
  public static final String FIELD_TYPE = "type";
  public static final String FIELD_TYPE_AS_STRING = "typeAsString";

  public static final String ENTITY_NAME = "AccountingAccount";
  public static final String TABLE_NAME = "TA_COMPTE_COMPTABLE";

  public static final String COLUMN_PLAN = "PLAN_COMPTABLE";
  public static final String COLUMN_TYPE = "TYPE";

  public static final String QUERY_READ_PAYMENT_BY_SCHOOL_IDENTIFIER_IDENTIFIER =
      "AccountingAccount.readPaymentBySchoolIdentifier";
  public static final String QUERY_READ_PAYMENT_BY_SCHOOL_IDENTIFIER_VALUE =
      "SELECT t FROM AccountingAccount t "
          + "JOIN SchoolConfiguration s ON s.paymentAccountingAccount = t "
          + "WHERE s.schoolIdentifier = :schoolIdentifier";
}
