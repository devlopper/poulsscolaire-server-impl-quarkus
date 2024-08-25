package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente les montants de {@link Payment}.
 *
 * @author Christian
 *
 */
@Entity
@Immutable
@Subselect(PaymentAmounts.QUERY)
public class PaymentAmounts extends AbstractIdentifiable {

  @Column(name = COLUMN_TOTAL)
  public long total;

  public static final String QUERY = """
      SELECT
          TA_PAIEMENT.IDENTIFIANT
          ,montant.valeur AS TOTAL
      FROM TA_PAIEMENT
      JOIN (
        -- Total
        SELECT
          TA_PAIEMENT.IDENTIFIANT
          ,SUM(TA_PAIEMENT_FRAIS_AJUSTE.MONTANT) AS VALEUR
        FROM TA_PAIEMENT
        JOIN TA_PAIEMENT_FRAIS_AJUSTE ON TA_PAIEMENT_FRAIS_AJUSTE.PAIEMENT = TA_PAIEMENT.IDENTIFIANT
        GROUP BY TA_PAIEMENT.IDENTIFIANT
      ) AS montant ON montant.IDENTIFIANT = TA_PAIEMENT.IDENTIFIANT
        """;
  public static final String FIELD_TOTAL = "total";

  public static final String COLUMN_TOTAL = "TOTAL";
}
