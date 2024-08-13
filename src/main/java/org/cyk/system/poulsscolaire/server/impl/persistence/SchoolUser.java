package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente une école et un utilisateur.
 *
 * @author Christian
 *
 */
@Entity(name = SchoolUser.ENTITY_NAME)
@Immutable
@Subselect(SchoolUser.QUERY)
public class SchoolUser extends AbstractIdentifiable {

  @Column(name = COLUMN_SCHOOL_IDENTIFIER)
  public String schoolIdentifier;

  @Column(name = COLUMN_USER_IDENTIFIER)
  public String userIdentifier;

  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_USER_IDENTIFIER = "userIdentifier";

  public static final String ENTITY_NAME = "SchoolUser";

  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
  public static final String COLUMN_USER_IDENTIFIER = "UTILISATEUR";

  public static final String QUERY = """
      SELECT
          CONCAT(ecole.ecoleid,u.utilisateu_login) AS IDENTIFIANT
          ,ecole.ecoleid AS ECOLE
          ,u.utilisateu_login AS UTILISATEUR
      FROM ecoleviedbv2.ecole ecole
      JOIN ecoleviedbv2.utilisateur_has_personnel  up
          ON up.ecole_ecoleid = ecole.ecoleid
      JOIN ecoleviedbv2.utilisateur  u
          ON u.utilisateurid = up.utilisateur_utilisateurid
                                  """;
}
