package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente la quantité de {@link Stock}.
 *
 * @author Christian
 *
 */
@Entity
@Immutable
@Subselect(StockQuantity.QUERY)
@EqualsAndHashCode(callSuper = true)
public class StockQuantity extends AbstractIdentifiable {

  @Column(name = COLUMN_VALUE)
  public long value;

  public static final String QUERY = """
      SELECT t.IDENTIFIANT, SUM(s.QUANTITE) AS VALEUR
      FROM TA_STOCK t
      JOIN TA_STOCK_MOUVEMENT s ON s.STOCK = t.IDENTIFIANT
      GROUP BY t.IDENTIFIANT
      """;
  public static final String FIELD_VALUE = "value";

  public static final String COLUMN_VALUE = "VALEUR";
}
