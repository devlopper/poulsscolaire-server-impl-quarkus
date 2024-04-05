package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Cette classe représente une inscription.
 *
 * @author Christian
 *
 */
@Entity(name = Registration.ENTITY_NAME)
@Table(name = Registration.TABLE_NAME)
public class Registration extends AbstractIdentifiableCodableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_STUDENT, nullable = false)
  public Student student;
  
  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_SCHOOLING, nullable = false)
  public Schooling schooling;
  
  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_ASSIGNMENT_TYPE, nullable = false)
  public AssignmentType assignmentType;
  
  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_SENIORITY, nullable = false)
  public Seniority seniority;
  
  public static final String FIELD_STUDENT = "student";
  public static final String FIELD_SCHOOLING = "schooling";
  public static final String FIELD_ASSIGNMENT_TYPE = "assignmentType";
  public static final String FIELD_SENIORITY = "seniority";
  
  public static final String ENTITY_NAME = "Registration";
  public static final String TABLE_NAME = "TA_INSCRIPTION";
  
  public static final String COLUMN_STUDENT = "ELEVE";
  public static final String COLUMN_SCHOOLING = "SCOLARITE";
  public static final String COLUMN_ASSIGNMENT_TYPE = "TYPE_AFFECTATION";
  public static final String COLUMN_SENIORITY = "ANCIENNETE";
}
