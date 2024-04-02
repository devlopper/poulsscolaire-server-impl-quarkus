package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Cette classe représente un élève.
 *
 * @author Christian
 *
 */
@Entity(name = Student.ENTITY_NAME)
@Table(name = Student.TABLE_NAME)
public class Student extends AbstractIdentifiableAuditable {

  @Column(name = COLUMN_REGISTRATION_NUMBER)
  public String registrationNumber;
  
  @ManyToOne
  @NotNull
  @JoinColumn(name = COLUMN_IDENTITY, nullable = false)
  public Identity identity;
  
  public static final String FIELD_REGISTRATION_NUMBER = "registrationNumber";
  public static final String FIELD_IDENTITY = "identity";
  
  public static final String ENTITY_NAME = "Student";
  public static final String TABLE_NAME = "TA_ELEVE";
  
  public static final String COLUMN_REGISTRATION_NUMBER = "MATRICULE";
  public static final String COLUMN_IDENTITY = "IDENTITE";
}
