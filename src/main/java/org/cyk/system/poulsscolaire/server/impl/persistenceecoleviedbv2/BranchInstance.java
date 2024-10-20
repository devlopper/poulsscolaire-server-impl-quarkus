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
  public int identifier;
  
  @Column(name = "classelibelle")
  public String name;
  
  @Column(name = "ecole_ecoleid")
  public long schoolIdentifier;
  
  @Column(name = "branche_id")
  public int branchIdentifier;
  
  @Column(name = "visible")
  public int visible;
}
