package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Cette classe représente une inscription.
 *
 * @author Christian
 *
 */
@Entity
@Table(schema = Constant.SCHEMA_NAME, name = "inscriptions")
public class Registration {

  @Id
  @Column(name = "inscriptionsid")
  public long identifier;
  
  @Column(name = "ecole_ecoleid")
  public long schoolIdentifier;
  
  @Column(name = "annee_scolaire_annee_scolaireid")
  public int periodIdentifier;
  
  @Column(name = "branche_id")
  public long branchIdentifier;
  
  @Column(name = "eleve_eleveid")
  public long studentIdentifier;
}
