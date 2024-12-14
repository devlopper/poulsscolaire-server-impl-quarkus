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
 * Cette classe représente un paiement de frais ajusté.
 *
 * @author Christian
 *
 */
@Entity(name = StockMovement.ENTITY_NAME)
@Table(name = StockMovement.TABLE_NAME)
@Audited
@AuditOverrides(value = {@AuditOverride(forClass = AbstractIdentifiableAuditable.class),
    @AuditOverride(forClass = AbstractIdentifiable.class)})
@NamedQueries(value = {@NamedQuery(name = StockMovement.QUERY_SUM_QUANTITY_BY_STOCK_IDENTIFIER,
    query = StockMovement.QUERY_SUM_QUANTITY_BY_STOCK_VALUE)})
@EqualsAndHashCode(callSuper = true)
public class StockMovement extends AbstractIdentifiableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_STOCK, nullable = false)
  public Stock stock;

  @NotNull
  @Column(name = COLUMN_QUANTITY, nullable = false)
  public Integer quantity;

  @Column(name = COLUMN_REASON)
  public String reason;

  @Transient
  public String stockIdentifier;

  @Transient
  public String stockAsString;

  @Transient
  public String quantityAsString;

  public static final String FIELD_STOCK = "stock";
  public static final String FIELD_STOCK_IDENTIFIER = "stockIdentifier";
  public static final String FIELD_STOCK_AS_STRING = "stockAsString";
  public static final String FIELD_QUANTITY = "quantity";
  public static final String FIELD_QUANTITY_AS_STRING = "quantityAsString";
  public static final String FIELD_REASON = "reason";

  public static final String ENTITY_NAME = "StockMovement";
  public static final String TABLE_NAME = "TA_STOCK_MOUVEMENT";

  public static final String COLUMN_STOCK = "STOCK";
  public static final String COLUMN_QUANTITY = "QUANTITE";
  public static final String COLUMN_REASON = "MOTIF";

  public static final String QUERY_SUM_QUANTITY_BY_STOCK_IDENTIFIER =
      "StockMovement.sumQuantityByStock";
  public static final String QUERY_SUM_QUANTITY_BY_STOCK_VALUE =
      "SELECT COALESCE(SUM(t." + FIELD_QUANTITY + "),0) FROM " + ENTITY_NAME + " t WHERE t."
          + FIELD_STOCK + " = :" + FIELD_STOCK;
}
