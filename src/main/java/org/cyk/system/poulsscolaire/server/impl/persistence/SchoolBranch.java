package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente une école et une branche.
 *
 * @author Christian
 *
 */
@Entity(name = SchoolBranch.ENTITY_NAME)
@Immutable
@Subselect(SchoolBranch.QUERY)
public class SchoolBranch extends AbstractIdentifiable {

  @Column(name = COLUMN_SCHOOL_IDENTIFIER)
  public String schoolIdentifier;

  @Column(name = COLUMN_BRANCH_IDENTIFIER)
  public String branchIdentifier;

  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_BRANCH_IDENTIFIER = "branchIdentifier";

  public static final String ENTITY_NAME = "SchoolBranch";

  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
  public static final String COLUMN_BRANCH_IDENTIFIER = "BRANCHE";

  public static final String QUERY =
      """
          SELECT
            CONCAT(ecole.ecoleid,programme_ecole.id,branche.id) AS IDENTIFIANT
            ,ecole.ecoleid AS ECOLE
            ,branche.id AS BRANCHE
          FROM ecoleviedbv2.ecole ecole
          JOIN ecoleviedbv2.programme_ecole programme_ecole 
            ON programme_ecole.fk_ecole_id = ecole.ecoleid
          JOIN ecoleviedbv2.programme programme ON programme_ecole.fk_programme_id = programme.id
          JOIN ecoleviedbv2.Branche branche 
            ON programme.id = branche.fk_programme_id 
            AND branche.niveau_enseignement_id = ecole.Niveau_Enseignement_id
                                            """;
}
