package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Cette classe représente un programme.
 *
 * @author Christian
 *
 */
@Entity
@Table(schema = Constant.SCHEMA_NAME, name = "programme")
public class Program {

  @Id
  @Column(name = "id")
  public String identifier;
  
  @Column(name = "code")
  public String code;
  
  @Column(name = "libelle")
  public String name;
}
