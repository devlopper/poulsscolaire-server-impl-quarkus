package org.cyk.system.poulsscolaire.server.impl.persistence;

import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente le montant à payer de {@link Registration}.
 *
 * @author Christian
 *
 */
@Entity
@Immutable
@Subselect(RegistrationAmountToPay.QUERY)
public class RegistrationAmountToPay extends AbstractRegistrationAmount {

  public static final String QUERY = """
      SELECT TA_INSCRIPTION.IDENTIFIANT AS IDENTIFIANT
          ,SUM(TA_MONTANT.VALEUR) AS MONTANT
          ,SUM(TA_MONTANT.VALEUR_INSCRIPTION) AS MONTANT_INSCRIPTION
      FROM TA_INSCRIPTION
      JOIN TA_FRAIS_AJUSTE ON TA_FRAIS_AJUSTE.INSCRIPTION = TA_INSCRIPTION.IDENTIFIANT
      JOIN TA_MONTANT ON TA_MONTANT.IDENTIFIANT = TA_FRAIS_AJUSTE.MONTANT
          AND TA_MONTANT.FACULTATIF IS FALSE
      GROUP BY TA_INSCRIPTION.IDENTIFIANT
        """;
}
