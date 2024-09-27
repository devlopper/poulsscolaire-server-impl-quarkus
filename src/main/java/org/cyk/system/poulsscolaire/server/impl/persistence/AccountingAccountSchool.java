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
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente un compte comptable.
 *
 * @author Christian
 *
 */
@Entity(name = AccountingAccountSchool.ENTITY_NAME)
@Table(name = AccountingAccountSchool.TABLE_NAME,
    uniqueConstraints = {@UniqueConstraint(columnNames = {AccountingAccountSchool.COLUMN_ACCOUNT,
        AccountingAccountSchool.COLUMN_SCHOOL_IDENTIFIER})})
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class AccountingAccountSchool extends AbstractIdentifiableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_ACCOUNT, nullable = false)
  public AccountingAccount account;

  @NotNull
  @Column(name = COLUMN_SCHOOL_IDENTIFIER, nullable = false)
  public String schoolIdentifier;

  @Transient
  public String accountIdentifier;

  @Transient
  public String accountAsString;

  @Transient
  public String schoolAsString;

  public static final String FIELD_ACCOUNT = "account";
  public static final String FIELD_ACCOUNT_IDENTIFIER = "accountIdentifier";
  public static final String FIELD_ACCOUNT_AS_STRING = "accountAsString";

  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_SCHOOL_AS_STRING = "schoolAsString";

  public static final String ENTITY_NAME = "AccountingAccountSchool";
  public static final String TABLE_NAME = "TA_COMPTE_COMPTABLE_ECOLE";

  public static final String COLUMN_ACCOUNT = "COMPTE";
  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
}
