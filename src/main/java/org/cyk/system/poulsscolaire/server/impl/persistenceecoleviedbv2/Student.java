package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Cette classe représente un étudiant.
 *
 * @author Christian
 *
 */
@Entity
@Table(schema = Constant.SCHEMA_NAME, name = "eleve")
public class Student {

  @Id
  @Column(name = "eleveid")
  public long identifier;
  
  @Column(name = "eleve_matricule")
  public String registrationNumber;
}
