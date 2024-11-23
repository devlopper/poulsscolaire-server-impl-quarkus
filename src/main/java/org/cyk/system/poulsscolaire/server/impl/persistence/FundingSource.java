package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente une source de financement.
 *
 * @author Christian
 *
 */
@Entity(name = FundingSource.ENTITY_NAME)
@Table(name = FundingSource.TABLE_NAME,
    uniqueConstraints = {@UniqueConstraint(columnNames = {FundingSource.COLUMN_SCHOOL_IDENTIFIER,
        AbstractIdentifiableCodable.COLUMN_CODE})})
@AttributeOverrides(value = {@AttributeOverride(name = AbstractIdentifiableCodable.FIELD_CODE,
    column = @Column(name = AbstractIdentifiableCodable.COLUMN_CODE, nullable = false,
        unique = false))})
@NamedQueries(
    value = {@NamedQuery(name = FundingSource.QUERY_COUNT_BY_SCHOOL_IDENTIFIER_BY_CODE_IDENTIFIER,
        query = FundingSource.QUERY_COUNT_BY_SCHOOL_IDENTIFIER_BY_CODE_VALUE)})
@Audited
@AuditOverrides(
    value = {@AuditOverride(forClass = AbstractIdentifiableCodableNamableAuditable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodableNamable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodable.class),
        @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class FundingSource extends AbstractIdentifiableCodableNamableAuditable {

  @NotNull
  @Column(name = COLUMN_SCHOOL_IDENTIFIER, nullable = false)
  public String schoolIdentifier;

  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_SCHOOL_AS_STRING = "schoolAsString";

  public static final String ENTITY_NAME = "FundingSource";
  public static final String TABLE_NAME = "TA_SOURCE_FINANCEMENT";

  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";

  public static final String QUERY_COUNT_BY_SCHOOL_IDENTIFIER_BY_CODE_IDENTIFIER =
      "FundingSource.countBySchoolIdentifierByCode";
  public static final String QUERY_COUNT_BY_SCHOOL_IDENTIFIER_BY_CODE_VALUE = "SELECT COUNT(t."
      + FIELD_IDENTIFIER + ") FROM " + ENTITY_NAME + " t WHERE t." + FIELD_SCHOOL_IDENTIFIER
      + " = :" + FIELD_SCHOOL_IDENTIFIER + " AND t." + FIELD_CODE + " = :" + FIELD_CODE;
}
