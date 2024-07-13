package org.cyk.system.poulsscolaire.server.impl.persistence;

import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente le montant à payer de {@link AdjustedFee}.
 *
 * @author Christian
 *
 */
@Entity
@Immutable
@Subselect(AdjustedFeeAmountToPay.QUERY)
public class AdjustedFeeAmountToPay extends AbstractAdjustedFeeAmount {

  public static final String QUERY = """
      SELECT TA_FRAIS_AJUSTE.IDENTIFIANT AS IDENTIFIANT
          ,TA_RUBRIQUE.IDENTIFIANT AS RUBRIQUE
          ,TA_INSCRIPTION.IDENTIFIANT AS INSCRIPTION
          ,TA_SCOLARITE.IDENTIFIANT AS SCOLARITE
          ,TA_SCOLARITE.ECOLE AS ECOLE
          ,TA_MONTANT.VALEUR AS MONTANT
          ,TA_MONTANT.VALEUR_INSCRIPTION AS MONTANT_INSCRIPTION
      FROM TA_FRAIS_AJUSTE
      JOIN TA_MONTANT ON TA_MONTANT.IDENTIFIANT = TA_FRAIS_AJUSTE.MONTANT
          AND TA_MONTANT.FACULTATIF IS FALSE
      JOIN TA_FRAIS ON TA_FRAIS.IDENTIFIANT = TA_FRAIS_AJUSTE.FRAIS
      JOIN TA_RUBRIQUE ON TA_RUBRIQUE.IDENTIFIANT = TA_FRAIS.RUBRIQUE
      JOIN TA_INSCRIPTION ON TA_INSCRIPTION.IDENTIFIANT = TA_FRAIS_AJUSTE.INSCRIPTION
      JOIN TA_SCOLARITE ON TA_SCOLARITE.IDENTIFIANT = TA_INSCRIPTION.SCOLARITE
        """;
}
