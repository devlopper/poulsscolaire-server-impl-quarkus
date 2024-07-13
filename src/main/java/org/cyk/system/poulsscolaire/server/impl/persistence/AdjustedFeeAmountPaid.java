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
@Subselect(AdjustedFeeAmountPaid.QUERY)
public class AdjustedFeeAmountPaid extends AbstractAdjustedFeeAmount {

  public static final String QUERY = """
        SELECT TA_FRAIS_AJUSTE.IDENTIFIANT AS IDENTIFIANT
          ,TA_RUBRIQUE.IDENTIFIANT AS RUBRIQUE
          ,TA_INSCRIPTION.IDENTIFIANT AS INSCRIPTION
          ,TA_SCOLARITE.IDENTIFIANT AS SCOLARITE
          ,TA_SCOLARITE.ECOLE AS ECOLE
          ,SUM(TA_PAIEMENT_FRAIS_AJUSTE.MONTANT) AS MONTANT
          ,(CASE WHEN TA_MONTANT.VALEUR_INSCRIPTION <= SUM(TA_PAIEMENT_FRAIS_AJUSTE.MONTANT)
           THEN TA_MONTANT.VALEUR_INSCRIPTION 
           ELSE SUM(TA_PAIEMENT_FRAIS_AJUSTE.MONTANT) END)
            AS MONTANT_INSCRIPTION
        FROM TA_FRAIS_AJUSTE
        JOIN TA_MONTANT ON TA_MONTANT.IDENTIFIANT = TA_FRAIS_AJUSTE.MONTANT
          AND TA_MONTANT.FACULTATIF IS FALSE
        JOIN TA_FRAIS ON TA_FRAIS.IDENTIFIANT = TA_FRAIS_AJUSTE.FRAIS
        JOIN TA_RUBRIQUE ON TA_RUBRIQUE.IDENTIFIANT = TA_FRAIS.RUBRIQUE
        JOIN TA_INSCRIPTION ON TA_INSCRIPTION.IDENTIFIANT = TA_FRAIS_AJUSTE.INSCRIPTION
        JOIN TA_SCOLARITE ON TA_SCOLARITE.IDENTIFIANT = TA_INSCRIPTION.SCOLARITE
        JOIN TA_PAIEMENT ON TA_PAIEMENT.INSCRIPTION = TA_INSCRIPTION.IDENTIFIANT
        JOIN TA_PAIEMENT_FRAIS_AJUSTE ON TA_PAIEMENT_FRAIS_AJUSTE.PAIEMENT = TA_PAIEMENT.IDENTIFIANT
          AND TA_PAIEMENT_FRAIS_AJUSTE.FRAIS_AJUSTE = TA_FRAIS_AJUSTE.IDENTIFIANT
        GROUP BY TA_FRAIS_AJUSTE.IDENTIFIANT,TA_SCOLARITE.IDENTIFIANT,TA_INSCRIPTION.IDENTIFIANT
      """;
}
