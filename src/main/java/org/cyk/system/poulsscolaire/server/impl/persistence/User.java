package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 * Cette classe représente une école.
 *
 * @author Christian
 *
 */
@Entity(name = User.ENTITY_NAME)
@Immutable
@Subselect(User.QUERY)
public class User extends AbstractIdentifiable {

  @Column(name = COLUMN_PASS)
  public String pass;

  @Column(name = COLUMN_ROLES)
  public String roles;

  public static final String FIELD_PASS = "pass";
  public static final String FIELD_ROLES = "roles";

  public static final String ENTITY_NAME = "User";

  public static final String COLUMN_PASS = "PASSE";
  public static final String COLUMN_ROLES = "ROLES";

  public static final String QUERY =
      """
          SELECT
              u.utilisateu_login AS IDENTIFIANT
              ,u.utilisateur_mot_de_passe AS PASSE
              ,GROUP_CONCAT(p.profilcode SEPARATOR ', ') AS ROLES
          FROM ecoleviedbv2.utilisateur u
          LEFT JOIN ecoleviedbv2.utilisateur_has_personnel up 
            ON up.utilisateur_utilisateurid = u.utilisateurid
          LEFT JOIN ecoleviedbv2.profil p ON p.profilid = up.profil_profilid
          GROUP BY u.utilisateurid, u.utilisateu_login, u.utilisateur_mot_de_passe
                                      """;
}
