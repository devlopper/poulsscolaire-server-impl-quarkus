package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Cette classe représente une classe.
 *
 * @author Christian
 *
 */
@Entity
@Table(schema = Constant.SCHEMA_NAME, name = "classe")
public class BranchInstance {

  @Id
  @Column(name = "classeid")
  public long identifier;
  
  @Column(name = "classelibelle")
  public String name;
}
