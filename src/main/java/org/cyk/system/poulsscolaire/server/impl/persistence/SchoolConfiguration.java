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
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente une scolarité.
 *
 * @author Christian
 *
 */
@Entity(name = SchoolConfiguration.ENTITY_NAME)
@Table(name = SchoolConfiguration.TABLE_NAME)
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
@NamedQueries(
    value = {@NamedQuery(name = SchoolConfiguration.QUERY_READ_BY_SCHOOL_IDENTIFIER_IDENTIFIER,
        query = SchoolConfiguration.QUERY_READ_BY_SCHOOL_IDENTIFIER_VALUE)})
public class SchoolConfiguration extends AbstractIdentifiableAuditable {

  @NotNull
  @Column(name = COLUMN_SCHOOL_IDENTIFIER, nullable = false, unique = true)
  public String schoolIdentifier;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_PAYMENT_ACCOUNTING_ACCOUNT_IDENTIFIER, nullable = false)
  public AccountingAccount paymentAccountingAccount;

  /* Transient Fields */

  @Transient
  public String schoolAsString;

  @Transient
  public String paymentAccountingAccountIdentifier;

  @Transient
  public String paymentAccountingAccountAsString;

  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_SCHOOL_AS_STRING = "schoolAsString";
  public static final String FIELD_PAYMENT_ACCOUNTING_ACCOUNT = "paymentAccountingAccount";
  public static final String FIELD_PAYMENT_ACCOUNTING_ACCOUNT_IDENTIFIER =
      "paymentAccountingAccountIdentifier";
  public static final String FIELD_PAYMENT_ACCOUNTING_ACCOUNT_AS_STRING =
      "paymentAccountingAccountAsString";

  public static final String ENTITY_NAME = "SchoolConfiguration";
  public static final String TABLE_NAME = "TA_CONFIGURATION_ECOLE";

  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
  public static final String COLUMN_PAYMENT_ACCOUNTING_ACCOUNT_IDENTIFIER =
      "COMPTE_COMPTABLE_PAIEMENT";

  public static final String QUERY_READ_BY_SCHOOL_IDENTIFIER_IDENTIFIER =
      "SchoolConfiguration.readBySchoolIdentifier";
  public static final String QUERY_READ_BY_SCHOOL_IDENTIFIER_VALUE =
      "SELECT t FROM SchoolConfiguration t WHERE t.schoolIdentifier = :schoolIdentifier";
}
