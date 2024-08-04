package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Cette classe représente un programme et une école.
 *
 * @author Christian
 *
 */
@Entity
@Table(schema = Constant.SCHEMA_NAME, name = "PROGRAMME_ECOLE")
public class ProgramSchool {

  @Id
  @Column(name = "id")
  public long identifier;
  
  @Column(name = "fk_program_id")
  public String programIdentifier;
  
  @Column(name = "fk_ecole_id")
  public String schoolIdentifier;
  
}
