package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.segregation.HasDate;
import ci.gouv.dgbf.extension.core.segregation.HasDateAsString;
import ci.gouv.dgbf.extension.core.segregation.HasQuantityAsString;
import ci.gouv.dgbf.extension.core.segregation.HasStockAsString;
import ci.gouv.dgbf.extension.core.segregation.HasStockIdentifier;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.cyk.system.poulsscolaire.server.api.configuration.HasBranchInstanceAsString;
import org.cyk.system.poulsscolaire.server.api.configuration.HasBranchInstanceIdentifier;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditOverrides;
import org.hibernate.envers.Audited;

/**
 * Cette classe représente une distribution de stock.
 *
 * @author Christian
 *
 */
@Entity(name = StockDistribution.ENTITY_NAME)
@Table(name = StockDistribution.TABLE_NAME)
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableCodableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiableCodable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
@EqualsAndHashCode(callSuper = true)
public class StockDistribution extends AbstractIdentifiableCodableAuditable
    implements HasStockIdentifier, HasStockAsString, HasBranchInstanceIdentifier,
    HasBranchInstanceAsString, HasDate, HasDateAsString, HasQuantityAsString {
  
  /*
   * Stock
   */

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_STOCK, nullable = false)
  public Stock stock;

  @Getter
  @Setter
  @Transient
  public String stockIdentifier;

  @Getter
  @Setter
  @Transient
  public String stockAsString;

  /*
   * Branch instance
   */
  
  @Getter
  @Setter
  @Column(name = COLUMN_BRANCH_INSTANCE, nullable = false)
  public String branchInstanceIdentifier;

  @Getter
  @Setter
  @Transient
  public String branchInstanceAsString;

  /*
   * Date
   */
  
  @NotNull
  @Getter
  @Setter
  @Column(name = COLUMN_DATE, nullable = false)
  public LocalDateTime date;

  @Getter
  @Setter
  @Transient
  public String dateAsString;

  /*
   * Quantity
   */
  
  @Getter
  @Setter
  @Transient
  public String quantityAsString;

  public static final String FIELD_STOCK = "stock";
  
  public static final String ENTITY_NAME = "StockDistribution";
  public static final String TABLE_NAME = "TA_STOCK_DISTRIBUTION";

  public static final String COLUMN_STOCK = "STOCK";
  public static final String COLUMN_BRANCH_INSTANCE = "CLASSE";
  public static final String COLUMN_DATE = "DATE_";
}
