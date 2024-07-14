package org.cyk.system.poulsscolaire.server.impl.persistence;

import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente le montant payé de {@link Registration}.
 *
 * @author Christian
 *
 */
@Entity
@Immutable
@Subselect(RegistrationAmountPaid.QUERY)
public class RegistrationAmountPaid extends AbstractRegistrationAmount {

  public static final String QUERY = """
        SELECT TA_INSCRIPTION.IDENTIFIANT AS IDENTIFIANT
          ,SUM(TA_PAIEMENT_FRAIS_AJUSTE.MONTANT) AS MONTANT
          ,SUM(CASE WHEN TA_MONTANT.VALEUR_INSCRIPTION <= TA_PAIEMENT_FRAIS_AJUSTE.MONTANT
           THEN TA_MONTANT.VALEUR_INSCRIPTION
           ELSE TA_PAIEMENT_FRAIS_AJUSTE.MONTANT END)
            AS MONTANT_INSCRIPTION
        FROM TA_INSCRIPTION
        JOIN TA_FRAIS_AJUSTE ON TA_FRAIS_AJUSTE.INSCRIPTION = TA_INSCRIPTION.IDENTIFIANT
        JOIN TA_MONTANT ON TA_MONTANT.IDENTIFIANT = TA_FRAIS_AJUSTE.MONTANT
          AND TA_MONTANT.FACULTATIF IS FALSE
        JOIN TA_PAIEMENT ON TA_PAIEMENT.INSCRIPTION = TA_INSCRIPTION.IDENTIFIANT
        JOIN TA_PAIEMENT_FRAIS_AJUSTE ON TA_PAIEMENT_FRAIS_AJUSTE.PAIEMENT = TA_PAIEMENT.IDENTIFIANT
          AND TA_PAIEMENT_FRAIS_AJUSTE.FRAIS_AJUSTE = TA_FRAIS_AJUSTE.IDENTIFIANT
        GROUP BY TA_INSCRIPTION.IDENTIFIANT
      """;
}
