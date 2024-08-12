package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Cette classe représente un role.
 *
 * @author Christian
 *
 */
@Entity
@Table(schema = Constant.SCHEMA_NAME, name = "profil")
public class Role {

  @Id
  @Column(name = "profilid")
  public int identifier;
  
  @Column(name = "profilcode")
  public String code;
  
  @Column(name = "profil_libelle")
  public String name;
  
}
