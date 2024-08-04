package org.cyk.system.poulsscolaire.server.impl.persistenceecoleviedbv2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Cette classe représente une année scolaire.
 *
 * @author Christian
 *
 */
@Entity
@Table(schema = Constant.SCHEMA_NAME, name = "ANNEE_SCOLAIRE")
public class AcademicYear {

  @Id
  @Column(name = "annee_scolaireid")
  public int identifier;
  
  @Column(name = "annee_scolaire_code")
  public String code;
  
  @Column(name = "annee_scolaire_libelle")
  public String name;
  
  @Column(name = "statut")
  public String status;
  
}
