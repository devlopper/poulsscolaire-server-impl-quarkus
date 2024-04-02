package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe représente une identité.
 *
 * @author Christian
 *
 */
@Entity(name = Identity.ENTITY_NAME)
@Table(name = Identity.TABLE_NAME)
@Getter
@Setter
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
  
  public static final String FIELD_FIRST_NAME = "firstName";
  public static final String FIELD_LAST_NAMES = "lastNames";
  public static final String FIELD_EMAIL_ADDRESS = "emailAddress";
  public static final String FIELD_PHONE_NUMBER = "phoneNumber";
  public static final String FIELD_GENDER = "gender";
  public static final String FIELD_IS_MASCULINE = "isMasculine";
  
  public static final String ENTITY_NAME = "Identity";
  public static final String TABLE_NAME = "TA_IDENTITE";
  
  public static final String COLUMN_FIRST_NAME = "nom";
  public static final String COLUMN_LAST_NAMES = "prenoms";
  public static final String COLUMN_EMAIL_ADDRESS = "adresseEmail";
  public static final String COLUMN_PHONE_NUMBER = "numeroTelephone";
  public static final String COLUMN_GENDER = "genre";
  
}
