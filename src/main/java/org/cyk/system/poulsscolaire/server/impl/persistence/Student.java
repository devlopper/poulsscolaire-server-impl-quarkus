package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.cyk.system.poulsscolaire.server.api.registration.BloodGroup;

/**
 * Cette classe représente un élève.
 *
 * @author Christian
 *
 */
@Entity(name = Student.ENTITY_NAME)
@Table(name = Student.TABLE_NAME)
public class Student extends AbstractIdentifiableCodableAuditable {

  @ManyToOne
  @NotNull
  @JoinColumn(name = COLUMN_IDENTITY, nullable = false)
  public Identity identity;
  
  @NotNull
  @Column(name = COLUMN_SCHOOL_IDENTIFIER, nullable = false)
  public String schoolIdentifier;
  
  @Column(name = COLUMN_ORIGIN_SCHOOL)
  public String originSchool;
  
  @Transient
  public String registrationNumber;
  
  @Transient
  public String firstName;
  
  @Transient
  public String arabicFirstName;
  
  @Transient
  public String lastNames;
  
  @Transient
  public String arabicLastNames;
  
  @Transient
  public BloodGroup bloodGroup;
  
  @Transient
  public String genderIdentifier;
  
  @Transient
  public String genderAsString;
  
  @Transient
  public LocalDateTime birthDate;
  
  @Transient
  public String birthDateAsString;
  
  @Transient
  public String birthPlace;
  
  @Transient
  public String birthCertificateReference;
  
  @Transient
  public String nationality;
  
  @Transient
  public Boolean isMasculine;
  
  @Transient
  public String emailAddress;
  
  @Transient
  public String phoneNumber;
  
  @Transient
  public String residence;
  
  @Transient
  public String othersContacts;
  
  @Transient
  public String fatherAsString;
  
  @Transient
  public String motherAsString;
  
  @Transient
  public String tutorAsString;
  
  @Transient
  public String schoolAsString;
  
  public static final String FIELD_IDENTITY = "identity";
  public static final String FIELD_REGISTRATION_NUMBER = "registrationNumber";
  public static final String FIELD_FIRST_NAME = "firstName";
  public static final String FIELD_ARABIC_FIRST_NAME = "arabicFirstName";
  public static final String FIELD_LAST_NAMES = "lastNames";
  public static final String FIELD_ARABIC_LAST_NAMES = "arabicLastNames";
  public static final String FIELD_BLOOD_GROUP = "bloodGroup";
  public static final String FIELD_GENDER_IDENTIFIER = "genderIdentifier";
  public static final String FIELD_GENDER_AS_STRING = "genderAsString";
  public static final String FIELD_NATIONALITY = "nationality";
  public static final String FIELD_IS_MASCULINE = "isMasculine";
  public static final String FIELD_BIRTH_DATE = "birthDate";
  public static final String FIELD_BIRTH_DATE_AS_STRING = "birthDateAsString";
  public static final String FIELD_BIRTH_PLACE = "birthPlace";
  public static final String FIELD_BIRTH_CERTIFICATE_REFERENCE = "birthCertificateReference";
  
  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_SCHOOL_AS_STRING = "schoolAsString";
  public static final String FIELD_ORIGIN_SCHOOL = "originSchool";
  
  public static final String FIELD_EMAIL_ADDRESS = "emailAddress";
  public static final String FIELD_PHONE_NUMBER = "phoneNumber";
  public static final String FIELD_RESIDENCE = "residence";
  public static final String FIELD_OTHERS_CONTACTS = "othersContacts";
  
  public static final String ENTITY_NAME = "Student";
  public static final String TABLE_NAME = "TA_ELEVE";
  
  public static final String COLUMN_IDENTITY = "IDENTITE";
  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
  public static final String COLUMN_ORIGIN_SCHOOL = "ECOLE_ORIGINE";
}
