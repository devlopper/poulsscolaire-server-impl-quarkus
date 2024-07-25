package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

/**
 * Cette classe représente un élève.
 *
 * @author Christian
 *
 */
@Entity(name = Student.ENTITY_NAME)
@Table(name = Student.TABLE_NAME)
public class Student extends AbstractIdentifiableCodableAuditable {

  @Column(name = COLUMN_REGISTRATION_NUMBER)
  public String registrationNumber;
  
  @ManyToOne
  @NotNull
  @JoinColumn(name = COLUMN_IDENTITY, nullable = false)
  public Identity identity;
  
  @Column(name = COLUMN_SCHOOL_IDENTIFIER, nullable = false)
  public String schoolIdentifier;
  
  @Transient
  public String firstName;
  
  @Transient
  public String lastNames;
  
  @Transient
  public String emailAddress;
  
  @Transient
  public String phoneNumber;
  
  @Transient
  public String genderIdentifier;
  
  @Transient
  public String genderAsString;
  
  @Transient
  public String birthDateAsString;
  
  @Transient
  public String birthPlace;
  
  @Transient
  public Boolean isMasculine;
  
  @Transient
  public String fatherAsString;
  
  @Transient
  public String motherAsString;
  
  @Transient
  public String tutorAsString;
  
  public static final String FIELD_REGISTRATION_NUMBER = "registrationNumber";
  public static final String FIELD_IDENTITY = "identity";
  public static final String FIELD_FIRST_NAME = "firstName";
  public static final String FIELD_LAST_NAMES = "lastNames";
  public static final String FIELD_EMAIL_ADDRESS = "emailAddress";
  public static final String FIELD_PHONE_NUMBER = "phoneNumber";
  public static final String FIELD_GENDER_IDENTIFIER = "genderIdentifier";
  public static final String FIELD_GENDER_AS_STRING = "genderAsString";
  public static final String FIELD_BIRTH_DATE_AS_STRING = "birthDateAsString";
  public static final String FIELD_BIRTH_PLACE = "birthPlace";
  public static final String FIELD_IS_MASCULINE = "isMasculine";
  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  
  public static final String ENTITY_NAME = "Student";
  public static final String TABLE_NAME = "TA_ELEVE";
  
  public static final String COLUMN_REGISTRATION_NUMBER = "MATRICULE";
  public static final String COLUMN_IDENTITY = "IDENTITE";
  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
}
