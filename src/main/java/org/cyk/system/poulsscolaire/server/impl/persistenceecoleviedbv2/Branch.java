package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Cette classe représente une branche.
 *
 * @author Christian
 *
 */
@Entity
@Table(schema = Constant.SCHEMA_NAME, name = "Branche")
public class Branch {

  @Id
  @Column(name = "id")
  public long identifier;
  
  @Column(name = "code")
  public String code;
  
  @Column(name = "libelle")
  public String name;
  
  @Column(name = "fk_programme_id")
  public String programIdentifier;
  
}
