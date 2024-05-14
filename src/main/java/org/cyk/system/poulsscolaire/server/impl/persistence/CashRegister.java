package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

/**
 * Cette classe représente une caisse enregistreuse.
 *
 * @author Christian
 *
 */
@Entity(name = CashRegister.ENTITY_NAME)
@Table(name = CashRegister.TABLE_NAME)
public class CashRegister extends AbstractIdentifiableCodableNamableAuditable {

  @NotNull
  @Column(name = COLUMN_SCHOOL_IDENTIFIER, nullable = false)
  public String schoolIdentifier;
  
  /* Transient Fields */

  @Transient
  public String schoolAsString;
  
  @Transient
  public String amountAsString;
  
  public static final String FIELD_SCHOOL_IDENTIFIER = "schoolIdentifier";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";
  
  public static final String ENTITY_NAME = "CashRegister";
  public static final String TABLE_NAME = "TA_CAISSE_ENREGISTREUSE";
  
  public static final String COLUMN_SCHOOL_IDENTIFIER = "ECOLE";
}
