package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
    
  @Transient
  public String studentAsString;
  
  @Transient
  public String schoolingAsString;
  
  @Transient
  public String assignmentTypeAsString;
  
  @Transient
  public String seniorityAsString;
  
  @Transient
  public Integer notOptionalFeeAmountValue;

  @Transient
  public String notOptionalFeeAmountValueAsString;

  @Transient
  public Integer notOptionalFeeAmountRegistrationValuePart;

  @Transient
  public String notOptionalFeeAmountRegistrationValuePartAsString;
  
  public static final String FIELD_STUDENT = "student";
  public static final String FIELD_STUDENT_AS_STRING = "studentAsString";
  public static final String FIELD_SCHOOLING = "schooling";
  public static final String FIELD_SCHOOLING_AS_STRING = "schoolingAsString";
  public static final String FIELD_ASSIGNMENT_TYPE = "assignmentType";
  public static final String FIELD_ASSIGNMENT_TYPE_AS_STRING = "assignmentTypeAsString";
  public static final String FIELD_SENIORITY = "seniority";
  public static final String FIELD_SENIORITY_AS_STRING = "seniorityAsString";
  public static final String FIELD_NOT_OPTIONAL_FEE_AMOUNT_VALUE = "notOptionalFeeAmountValue";
  public static final String FIELDT_NOT_OPTIONAL_FEE_AMOUN_VALUE_AS_STRING =
      "notOptionalFeeAmountValueAsString";
  public static final String FIELD_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART =
      "notOptionalFeeAmountRegistrationValuePart";
  public static final String FIELD_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING =
      "notOptionalFeeAmountRegistrationValuePartAsString";
  
  public static final String ENTITY_NAME = "Registration";
  public static final String TABLE_NAME = "TA_INSCRIPTION";
  
  public static final String COLUMN_STUDENT = "ELEVE";
  public static final String COLUMN_SCHOOLING = "SCOLARITE";
  public static final String COLUMN_ASSIGNMENT_TYPE = "TYPE_AFFECTATION";
  public static final String COLUMN_SENIORITY = "ANCIENNETE";
}
