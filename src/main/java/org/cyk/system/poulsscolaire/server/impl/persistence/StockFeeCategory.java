package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.segregation.HasFeeCategoryAsString;
import ci.gouv.dgbf.extension.core.segregation.HasFeeCategoryIdentifier;
import ci.gouv.dgbf.extension.core.segregation.HasStockAsString;
import ci.gouv.dgbf.extension.core.segregation.HasStockIdentifier;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente {@link FeeCategory} de {@link Stock}.
 *
 * @author Christian
 *
 */
@Getter
@Setter
@Entity(name = StockFeeCategory.ENTITY_NAME)
@Table(name = StockFeeCategory.TABLE_NAME,
    uniqueConstraints = {@UniqueConstraint(
        columnNames = {StockFeeCategory.COLUMN_STOCK, StockFeeCategory.COLUMN_FEE_CATEGORY})})
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class StockFeeCategory extends AbstractIdentifiableAuditable implements HasStockIdentifier,
    HasStockAsString, HasFeeCategoryIdentifier, HasFeeCategoryAsString {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_STOCK, nullable = false)
  private Stock stock;

  @Transient
  private String stockIdentifier;

  @Transient
  private String stockAsString;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_FEE_CATEGORY, nullable = false)
  private FeeCategory feeCategory;

  @Transient
  private String feeCategoryIdentifier;

  @Transient
  private String feeCategoryAsString;

  public static final String FIELD_FEE_CATEGORY = "feeCategory";
  public static final String FIELD_FEE_CATEGORY_IDENTIFIER = "feeCategoryIdentifier";
  public static final String FIELD_FEE_CATEGORY_AS_STRING = "feeCategoryAsString";
  public static final String FIELD_STOCK = "stock";
  public static final String FIELD_STOCK_IDENTIFIER = "stockIdentifier";
  public static final String FIELD_STOCK_AS_STRING = "stockAsString";

  public static final String ENTITY_NAME = "StockFeeCategory";
  public static final String TABLE_NAME = "TA_STOCK_RUBRIQUE";

  public static final String COLUMN_STOCK = "STOCK";
  public static final String COLUMN_FEE_CATEGORY = "RUBRIQUE";
}
