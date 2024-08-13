package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Cette classe représente un role d'utilisateur.
 *
 * @author Christian
 *
 */
@Entity
@Table(schema = Constant.SCHEMA_NAME, name = "utilisateur_has_personnel")
public class UserRole {

  @Id
  @Column(name = "utilisateur_has_personnelid")
  public long identifier;
  
  @Column(name = "utilisateur_utilisateurid")
  public int userIdentifier;
  
  @Column(name = "profil_profilid")
  public int roleIdentifier;
  
}
