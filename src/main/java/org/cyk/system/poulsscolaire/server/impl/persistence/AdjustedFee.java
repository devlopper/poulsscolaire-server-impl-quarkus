package org.cyk.system.poulsscolaire.server.impl.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

/**
 * Cette classe représente un frais ajusté.
 *
 * @author Christian
 *
 */
@Entity(name = AdjustedFee.ENTITY_NAME)
@Table(name = AdjustedFee.TABLE_NAME)
public class AdjustedFee extends AbstractAmountContainer {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_FEE, nullable = false)
  public Fee fee;
  
  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_REGISTRATION, nullable = false)
  public Registration registration;
        
  @Transient
  public String feeAsString;
  
  @Transient
  public String registrationAsString;
  
  public static final String FIELD_FEE = "fee";
  public static final String FIELD_FEE_AS_STRING = "feeAsString";
  public static final String FIELD_REGISTRATION = "registration";
  public static final String FIELD_REGISTRATION_AS_STRING = "registrationAsString";
  
  public static final String ENTITY_NAME = "AdjustedFee";
  public static final String TABLE_NAME = "TA_FRAIS_AJUSTE";
  
  public static final String COLUMN_FEE = "FRAIS";
  public static final String COLUMN_REGISTRATION = "INSCRIPTION";
}
