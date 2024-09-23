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
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente une compte comptable.
 *
 * @author Christian
 *
 */
@Entity(name = AccountingOperationAccount.ENTITY_NAME)
@Table(name = AccountingOperationAccount.TABLE_NAME,
    uniqueConstraints = {@UniqueConstraint(columnNames = {
        AccountingOperationAccount.COLUMN_OPERATION, AccountingOperationAccount.COLUMN_ACCOUNT})})
@AttributeOverrides(value = {@AttributeOverride(name = AbstractIdentifiableCodable.FIELD_CODE,
    column = @Column(name = AbstractIdentifiableCodable.COLUMN_CODE, unique = false))})
@Audited
@AuditOverrides(
    value = {@AuditOverride(forClass = AbstractIdentifiableCodableNamableAuditable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodableNamable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodable.class),
        @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class AccountingOperationAccount extends AbstractIdentifiableCodableNamableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = FIELD_OPERATION, nullable = false)
  public AccountingOperation operation;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_ACCOUNT, nullable = false)
  public AccountingAccount account;

  @NotNull
  @Column(name = COLUMN_AMOUNT, nullable = false)
  public Integer amount;
  
  @Transient
  public String operationIdentifier;

  @Transient
  public String operationAsString;

  @Transient
  public String accountIdentifier;

  @Transient
  public String accountAsString;

  @Transient
  public String amountAsString;
  
  public static final String FIELD_OPERATION = "operation";
  public static final String FIELD_OPERATION_IDENTIFIER = "operationIdentifier";
  public static final String FIELD_OPERATION_AS_STRING = "operationAsString";
  public static final String FIELD_ACCOUNT = "account";
  public static final String FIELD_ACCOUNT_IDENTIFIER = "accountIdentifier";
  public static final String FIELD_ACCOUNT_AS_STRING = "accountAsString";
  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";
  
  public static final String ENTITY_NAME = "AccountingOperationAccount";
  public static final String TABLE_NAME = "TA_OPERATION_COMPTE_COMPTABLE";

  public static final String COLUMN_OPERATION = "OPERATION";
  public static final String COLUMN_ACCOUNT = "COMPTE";
  public static final String COLUMN_AMOUNT = "MONTANT";
}
