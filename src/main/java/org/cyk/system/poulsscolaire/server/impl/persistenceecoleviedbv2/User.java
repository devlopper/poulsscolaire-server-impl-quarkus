package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Cette classe représente un utilisateur.
 *
 * @author Christian
 *
 */
@Entity
@Table(schema = Constant.SCHEMA_NAME, name = "UTILISATEUR")
public class User {

  @Id
  @Column(name = "utilisateurid")
  public int identifier;
  
  @Column(name = "utilisateu_login")
  public String name;
  
  @Column(name = "utilisateur_mot_de_passe")
  public String pass;
  
}
