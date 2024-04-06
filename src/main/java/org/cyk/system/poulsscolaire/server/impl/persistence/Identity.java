package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import org.cyk.system.poulsscolaire.server.api.IdentityService;

/**
 * Cette classe représente une identité.
 *
 * @author Christian
 *
 */
@Entity(name = Identity.ENTITY_NAME)
@Table(name = Identity.TABLE_NAME)
public class Identity extends AbstractIdentifiableAuditable {

  @Column(name = COLUMN_FIRST_NAME, nullable = false)
  @NotNull
  public String firstName;
  
  @Column(name = COLUMN_LAST_NAMES, nullable = false)
  @NotNull
  public String lastNames;
  
  @Column(name = COLUMN_EMAIL_ADDRESS)
  public String emailAddress;
  
  @Column(name = COLUMN_PHONE_NUMBER)
  public String phoneNumber;
  
  @ManyToOne
  @NotNull
  @JoinColumn(name = COLUMN_GENDER, nullable = false)
  public Gender gender;
  
  @Transient
  public Boolean isMasculine;
  
  /**
   * Cette méthode permet d'assigner les attributs.
   *
   * @param request requête
   * @param array tableau
   */
  public void set(IdentityService.IdentityData request, Object[] array) {
    firstName = request.getFirstName();
    lastNames = request.getLastNames();
    emailAddress = request.getEmailAddress();
    phoneNumber = request.getPhoneNumber();
    if (array != null) {
      gender = (Gender) array[0];
    }
  }
  
  public static final String FIELD_FIRST_NAME = "firstName";
  public static final String FIELD_LAST_NAMES = "lastNames";
  public static final String FIELD_EMAIL_ADDRESS = "emailAddress";
  public static final String FIELD_PHONE_NUMBER = "phoneNumber";
  public static final String FIELD_GENDER = "gender";
  public static final String FIELD_IS_MASCULINE = "isMasculine";
  
  public static final String ENTITY_NAME = "Identity";
  public static final String TABLE_NAME = "TA_IDENTITE";
  
  public static final String COLUMN_FIRST_NAME = "NOM";
  public static final String COLUMN_LAST_NAMES = "PRENOMS";
  public static final String COLUMN_EMAIL_ADDRESS = "ADRESSE_EMAIL";
  public static final String COLUMN_PHONE_NUMBER = "NUMERO_TELEPHONE";
  public static final String COLUMN_GENDER = "GENRE";
  
}
