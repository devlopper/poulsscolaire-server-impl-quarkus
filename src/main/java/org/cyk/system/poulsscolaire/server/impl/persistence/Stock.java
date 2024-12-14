package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
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
 * Cette classe représente un stock.
 *
 * @author Christian
 *
 */
@Entity(name = Stock.ENTITY_NAME)
@Table(name = Stock.TABLE_NAME)
@Audited
@AuditOverrides(
    value = {@AuditOverride(forClass = AbstractIdentifiableCodableNamableAuditable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodableNamable.class),
        @AuditOverride(forClass = AbstractIdentifiableCodable.class),
        @AuditOverride(forClass = AbstractIdentifiable.class)})
@NamedQueries(value = {@NamedQuery(name = Stock.QUERY_COUNT_BY_FEE_CATEGORY_IDENTIFIER,
    query = Stock.QUERY_COUNT_BY_FEE_CATEGORY_VALUE)})
@EqualsAndHashCode(callSuper = true)
public class Stock extends AbstractIdentifiableCodableNamableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_FEE_CATEGORY, nullable = false)
  public FeeCategory feeCategory;

  /* valeurs dérivées */

  @Transient
  public String feeCategoryIdentifier;

  @Transient
  public String feeCategoryAsString;

  @Transient
  public String quantityAsString;

  public static final String FIELD_FEE_CATEGORY = "feeCategory";

  public static final String FIELD_FEE_CATEGORY_IDENTIFIER = "feeCategoryIdentifier";
  public static final String FIELD_FEE_CATEGORY_AS_STRING = "feeCategoryAsString";
  public static final String FIELD_QUANTITY_AS_STRING = "quantityAsString";

  public static final String ENTITY_NAME = "Stock";
  public static final String TABLE_NAME = "TA_STOCK";

  public static final String COLUMN_FEE_CATEGORY = "RUBRIQUE";

  public static final String QUERY_COUNT_BY_FEE_CATEGORY_IDENTIFIER = "Stock.readByFeeCategory";
  public static final String QUERY_COUNT_BY_FEE_CATEGORY_VALUE = "SELECT COUNT(t) FROM "
      + ENTITY_NAME + " t WHERE t." + FIELD_FEE_CATEGORY + " = :" + FIELD_FEE_CATEGORY;
}
