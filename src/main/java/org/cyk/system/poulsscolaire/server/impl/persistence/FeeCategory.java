package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe représente une rubrique.
 *
 * @author Christian
 *
 */
@Getter
@Setter
@Entity(name = FeeCategory.ENTITY_NAME)
@Table(name = FeeCategory.TABLE_NAME,
    uniqueConstraints = {@UniqueConstraint(columnNames = {FeeCategory.COLUMN_SCHOOL_IDENTIFIER,
        AbstractIdentifiableCodable.COLUMN_CODE})})
@AttributeOverrides(value = {@AttributeOverride(name = AbstractIdentifiableCodable.FIELD_CODE,
    column = @Column(name = AbstractIdentifiableCodable.COLUMN_CODE, nullable = false,
        unique = false))})
@NamedQueries(
    value = {@NamedQuery(name = FeeCategory.QUERY_COUNT_BY_SCHOOL_IDENTIFIER_BY_CODE_IDENTIFIER,
        query = FeeCategory.QUERY_COUNT_BY_SCHOOL_IDENTIFIER_BY_CODE_VALUE)})
public class FeeCategory extends AbstractIdentifiableCodableNamableAuditable {

  @NotNull
  @Column(name = COLUMN_SCHOOL_IDENTIFIER, nullable = false)
  public String schoolIdentifier;

  @Transient
  public String schoolAsString;

  /*
   * Amount
   */
  @Transient
  public String totalAmountAsString;

  @Transient
  public String paidAmountAsString;

  @Transient
  public String payableAmountAsString;

  /*
   * Registration amount
   */
  @Transient
  public String totalRegistrationAmountAsString;

  @Transient
  public String paidRegistrationAmountAsString;

  @Transient
  public String payableRegistrationAmountAsString;

  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_SCHOOL_AS_STRING = "schoolAsString";

  public static final String ENTITY_NAME = "FeeCategory";
  public static final String TABLE_NAME = "TA_RUBRIQUE";

  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";

  public static final String QUERY_COUNT_BY_SCHOOL_IDENTIFIER_BY_CODE_IDENTIFIER =
      "FeeCategory.existBySchoolIdentifierByCode";
  public static final String QUERY_COUNT_BY_SCHOOL_IDENTIFIER_BY_CODE_VALUE = "SELECT COUNT(t."
      + FIELD_IDENTIFIER + ") FROM " + ENTITY_NAME + " t WHERE t." + FIELD_SCHOOL_IDENTIFIER
      + " = :" + FIELD_SCHOOL_IDENTIFIER + " AND t." + FIELD_CODE + " = :" + FIELD_CODE;
}
