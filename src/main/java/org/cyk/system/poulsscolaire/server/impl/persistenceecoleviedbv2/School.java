package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Cette classe représente une école.
 *
 * @author Christian
 *
 */
@Entity
@Table(schema = Constant.SCHEMA_NAME, name = "ECOLE")
public class School {

  @Id
  @Column(name = "ecoleid")
  public long identifier;
  
  @Column(name = "ecolecode")
  public String code;
  
  @Column(name = "ecoleclibelle")
  public String name;
  
}
