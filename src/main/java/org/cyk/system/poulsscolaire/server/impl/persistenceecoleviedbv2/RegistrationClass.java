package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Cette classe représente une inscription dans une classe.
 *
 * @author Christian
 *
 */
@Entity
@Table(schema = Constant.SCHEMA_NAME, name = "inscriptions_has_classe")
public class RegistrationClass {

  @Id
  @Column(name = "id")
  public long identifier;
  
  @Column(name = "inscriptions_inscriptionsid")
  public long registrationIdentifier;
  
  @Column(name = "classe_classeid")
  public long classIdentifier;
}
