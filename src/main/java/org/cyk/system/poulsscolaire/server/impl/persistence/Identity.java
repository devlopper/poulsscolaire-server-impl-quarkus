package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import org.cyk.system.poulsscolaire.server.api.registration.BloodGroup;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentitySaveRequest;

/**
 * Cette classe représente une identité.
 *
 * @author Christian
 *
 */
@Entity(name = Identity.ENTITY_NAME)
@Table(name = Identity.TABLE_NAME)
public class Identity extends AbstractIdentifiableAuditable {

  @Column(name = COLUMN_REGISTRATION_NUMBER, unique = true)
  public String registrationNumber;
  
  @Column(name = COLUMN_FIRST_NAME, nullable = false)
  @NotNull
  public String firstName;
  
  @Column(name = COLUMN_ARABIC_FIRST_NAME)
  public String arabicFirstName;
  
  @Column(name = COLUMN_LAST_NAMES, nullable = false)
  @NotNull
  public String lastNames;
  
  @Column(name = COLUMN_ARABIC_LAST_NAMES)
  public String arabicLastNames;
  
  @ManyToOne
  @NotNull
  @JoinColumn(name = COLUMN_GENDER, nullable = false)
  public Gender gender;
  
  @Transient
  public Boolean isMasculine;
  
  @Column(name = COLUMN_BLOOD_GROUP)
  public BloodGroup bloodGroup;
  
  @Column(name = COLUMN_BIRTH_DATE)
  public LocalDateTime birthDate;
  
  @Column(name = COLUMN_BIRTH_PLACE)
  public String birthPlace;
  
  @Column(name = COLUMN_BIRTH_CERTIFICATE_REFERENCE)
  public String birthCertificateReference;
  
  @Column(name = COLUMN_NATIONALITY)
  public String nationality;
  
  @Column(name = COLUMN_RESIDENCE)
  public String residence;
  
  @Column(name = COLUMN_SITUATION)
  public String situation;
  
  @Column(name = COLUMN_PROFESSION)
  public String profession;
  
  @Column(name = COLUMN_EMAIL_ADDRESS)
  public String emailAddress;
  
  @Column(name = COLUMN_PHONE_NUMBER)
  public String phoneNumber;
  
  @Column(name = COLUMN_OTHERS_CONTACTS)
  public String othersContacts;
  
  /**
   * Cette méthode permet d'assigner les attributs.
   *
   * @param request requête
   * @param array tableau
   */
  public void set(IdentitySaveRequest request, Object[] array) {
    registrationNumber = request.getRegistrationNumber();
    
    firstName = request.getFirstName();
    arabicFirstName = request.getArabicFirstName();
    lastNames = request.getLastNames();
    arabicLastNames = request.getArabicLastNames();
    if (array != null) {
      gender = (Gender) array[0];
    }
    bloodGroup = request.getBloodGroup();
    
    birthDate = request.getBirthDate();
    birthPlace = request.getBirthPlace();
    birthCertificateReference = request.getBirthCertificateReference();
    
    nationality = request.getNationality();
    residence = request.getResidence();
    profession = request.getProfession();
    situation = request.getSituation();
    
    emailAddress = request.getEmailAddress();
    phoneNumber = request.getPhoneNumber();
    othersContacts = request.getOthersContacts();
  }
  
  public static final String FIELD_REGISTRATION_NUMBER = "registrationNumber";
  public static final String FIELD_FIRST_NAME = "firstName";
  public static final String FIELD_ARABIC_FIRST_NAME = "arabicFirstName";
  public static final String FIELD_LAST_NAMES = "lastNames";
  public static final String FIELD_ARABIC_LAST_NAMES = "arabicLastNames";
  public static final String FIELD_EMAIL_ADDRESS = "emailAddress";
  public static final String FIELD_PHONE_NUMBER = "phoneNumber";
  public static final String FIELD_GENDER = "gender";
  public static final String FIELD_IS_MASCULINE = "isMasculine";
  public static final String FIELD_BIRTH_DATE = "birthDate";
  public static final String FIELD_BIRTH_PLACE = "birthPlace";
  public static final String FIELD_BIRTH_CERTIFICATE_REFERENCE = "birthCertificateReference";
  public static final String FIELD_BLOOD_GROUP = "bloodGroup";
  public static final String FIELD_NATIONALITY = "nationality";
  public static final String FIELD_RESIDENCE = "residence";
  public static final String FIELD_SITUATION = "situation";
  public static final String FIELD_PROFESSION = "profession";
  public static final String FIELD_OTHERS_CONTACTS = "othersContacts";
  
  public static final String ENTITY_NAME = "Identity";
  public static final String TABLE_NAME = "TA_IDENTITE";
  
  public static final String COLUMN_REGISTRATION_NUMBER = "MATRICULE";
  public static final String COLUMN_FIRST_NAME = "NOM";
  public static final String COLUMN_ARABIC_FIRST_NAME = "NOM_ARABE";
  public static final String COLUMN_LAST_NAMES = "PRENOMS";
  public static final String COLUMN_ARABIC_LAST_NAMES = "PRENOMS_ARABE";
  
  public static final String COLUMN_GENDER = "GENRE";
  public static final String COLUMN_BIRTH_DATE = "DATE_NAISSANCE";
  public static final String COLUMN_BIRTH_PLACE = "LIEU_NAISSANCE";
  public static final String COLUMN_BIRTH_CERTIFICATE_REFERENCE = "REFERENCE_EXTRAIT_NAISSANCE";
  public static final String COLUMN_BLOOD_GROUP = "GROUPE_SANGUIN";
  public static final String COLUMN_NATIONALITY = "NATIONALITE";
  public static final String COLUMN_SITUATION = "SITUATION";
  public static final String COLUMN_PROFESSION = "PROFESSION";
  
  public static final String COLUMN_EMAIL_ADDRESS = "ADRESSE_EMAIL";
  public static final String COLUMN_PHONE_NUMBER = "NUMERO_TELEPHONE";
  public static final String COLUMN_RESIDENCE = "LIEU_RESIDENCE";
  public static final String COLUMN_OTHERS_CONTACTS = "AUTRES_CONTACTS";
}
