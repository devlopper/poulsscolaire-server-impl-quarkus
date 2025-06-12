package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableIntValueBasedQuery;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente la quantité de {@link StockDistribution}.
 *
 * @author Christian
 *
 */
@Entity
@Immutable
@Subselect(StockDistributionQuantity.QUERY)
@EqualsAndHashCode(callSuper = true)
public class StockDistributionQuantity extends AbstractIdentifiableIntValueBasedQuery {

  public static final String QUERY = """
      SELECT t.IDENTIFIANT, SUM(m.QUANTITE) AS VALEUR
      FROM TA_STOCK_DISTRIBUTION t
      JOIN TA_STOCK_DISTRIBUTION_INSCRIPTION s ON s.DISTRIBUTION = t.IDENTIFIANT
      JOIN TA_STOCK_MOUVEMENT m ON m.IDENTIFIANT = s.MOUVEMENT
      GROUP BY t.IDENTIFIANT
      """;
}
