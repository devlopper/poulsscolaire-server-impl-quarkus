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
@Table(schema = Constant.SCHEMA_NAME, name = "annee_scolaire")
public class AcademicYear {

  @Id
  @Column(name = "annee_scolaireid")
  public int identifier;
  
  @Column(name = "annee_scolaire_code")
  public String code;
  
  @Column(name = "annee_scolaire_libelle")
  public String name;
  
  @Column(name = "ecole_id")
  public long schoolIdentifier;
  
  @Column(name = "annee")
  public String year;
  
  @Column(name = "niveau")
  public String level;
  
  @Column(name = "niveau_enseignement_id")
  public int teachingLevelIdentifier;
  
  @Column(name = "statut")
  public String status;
  
}
