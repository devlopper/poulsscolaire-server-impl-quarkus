package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente une école et une période.
 *
 * @author Christian
 *
 */
@Entity(name = SchoolPeriod.ENTITY_NAME)
@Immutable
@Subselect(SchoolPeriod.QUERY)
public class SchoolPeriod extends AbstractIdentifiable {

  @Column(name = COLUMN_SCHOOL_IDENTIFIER)
  public String schoolIdentifier;

  @Column(name = COLUMN_PERIOD_IDENTIFIER)
  public String periodIdentifier;
  
  @Column(name = COLUMN_OPENED)
  public boolean opened;

  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_PERIOD_IDENTIFIER = "periodIdentifier";
  public static final String FIELD_PERIOD_OPENED = "opened";

  public static final String ENTITY_NAME = "SchoolPeriod";

  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
  public static final String COLUMN_PERIOD_IDENTIFIER = "PERIODE";
  public static final String COLUMN_OPENED = "OUVERTE";

  public static final String QUERY =
      """
          SELECT
              CONCAT(ecole.ecoleid,anneeCentral.annee_scolaireid) AS IDENTIFIANT
              ,ecole.ecoleid AS ECOLE
              ,anneeCentral.annee_scolaireid AS ANNEE_SCOLAIRE
              ,CASE anneeEcole.statut WHEN 'OUVERT' THEN 1 ELSE 0 END AS OUVERTE
          FROM ecoleviedbv2.ecole
          JOIN ecoleviedbv2.annee_scolaire anneeCentral 
              ON anneeCentral.niveau_enseignement_id = ecole.Niveau_Enseignement_id
              AND anneeCentral.niveau = 'CENTRAL'
          JOIN ecoleviedbv2.annee_scolaire anneeEcole ON anneeEcole.ecole_id = ecole.ecoleid
              AND anneeEcole.annee = anneeCentral.annee
                                                        """;
}
