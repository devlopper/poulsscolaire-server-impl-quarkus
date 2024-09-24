package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountType;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente une opération comptable.
 *
 * @author Christian
 *
 */
@Getter
@Setter
@Entity(name = AccountingOperation.ENTITY_NAME)
@Table(name = AccountingOperation.TABLE_NAME)
@Audited
@AuditOverrides(
    value = {@AuditOverride(forClass = AbstractIdentifiableCodableNamableAuditable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodableNamable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodable.class),
        @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class AccountingOperation extends AbstractIdentifiableCodableNamableAuditable {

  @Column(name = COLUMN_SCHOOL_IDENTIFIER, nullable = false)
  public String schoolIdentifier;

  @Column(name = COLUMN_ACCOUNT_TYPE, nullable = false)
  public AccountingAccountType accountType;

  @Column(name = COLUMN_BENEFICIARY, nullable = false)
  public String beneficiary;

  @Transient
  public String schoolAsString;

  @Transient
  public String accountTypeAsString;

  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_SCHOOL_AS_STRING = "schoolAsString";
  public static final String FIELD_ACCOUNT_TYPE = "accountType";
  public static final String FIELD_ACCOUNT_TYPE_AS_STRING = "accountTypeAsString";
  public static final String FIELD_BENEFICIARY = "beneficiary";

  public static final String ENTITY_NAME = "AccountingOperation";
  public static final String TABLE_NAME = "TA_OPERATION_COMPTABLE";

  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
  public static final String COLUMN_ACCOUNT_TYPE = "TYPE_COMPTE";
  public static final String COLUMN_BENEFICIARY = "BENEFICIAIRE";
}
