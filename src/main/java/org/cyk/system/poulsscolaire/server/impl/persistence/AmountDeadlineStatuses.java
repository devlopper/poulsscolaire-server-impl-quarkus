package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente les statuts de {@link AmountDeadline}.
 *
 * @author Christian
 *
 */
@Entity
@Immutable
@Subselect(AmountDeadlineStatuses.QUERY)
public class AmountDeadlineStatuses extends AbstractIdentifiable {

  @Column(name = "PASSEE")
  public boolean passed;

  @Column(name = "EN_COURS")
  public boolean running;

  public static final String QUERY = """
      SELECT TA_ECHEANCE_MONTANT.IDENTIFIANT
        ,PASSEE.VALEUR AS PASSEE,EN_COURS.VALEUR AS EN_COURS
      FROM TA_ECHEANCE_MONTANT
      LEFT JOIN
        (-- Passée
        SELECT TA_ECHEANCE_MONTANT.IDENTIFIANT AS ECHEANCE_MONTANT
          , CASE WHEN TA_ECHEANCE.DATE_ < NOW() THEN 1 ELSE 0 END AS VALEUR
        FROM TA_ECHEANCE_MONTANT
        JOIN TA_ECHEANCE ON TA_ECHEANCE.IDENTIFIANT = TA_ECHEANCE_MONTANT.ECHEANCE)
          AS PASSEE ON PASSEE.ECHEANCE_MONTANT = TA_ECHEANCE_MONTANT.IDENTIFIANT
      LEFT JOIN
        (-- En cours
        SELECT t.IDENTIFIANT AS ECHEANCE_MONTANT
          , CASE WHEN
            (SELECT MIN(s2.DATE_)
             FROM TA_ECHEANCE_MONTANT s1
             JOIN TA_ECHEANCE s2 ON s2.IDENTIFIANT = s1.ECHEANCE
             WHERE s1.MONTANT = t.MONTANT AND s2.DATE_ >= NOW()) = TA_ECHEANCE.DATE_
          THEN 1 ELSE 0 END AS VALEUR
        FROM TA_ECHEANCE_MONTANT t
        JOIN TA_ECHEANCE ON TA_ECHEANCE.IDENTIFIANT = t.ECHEANCE)
          AS EN_COURS ON EN_COURS.ECHEANCE_MONTANT = TA_ECHEANCE_MONTANT.IDENTIFIANT
                              """;

  public static final String FIELD_PASSED = "passed";
  public static final String FIELD_RUNNING = "running";
}
