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
 * Cette classe représente un paiement.
 *
 * @author Christian
 *
 */
@Entity(name = Payment.ENTITY_NAME)
@Table(name = Payment.TABLE_NAME)
public class Payment extends AbstractIdentifiableCodableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_MODE, nullable = false)
  public PaymentMode mode;
  
  @NotNull
  @Column(name = COLUMN_AMOUNT, nullable = false)
  public int amount;
    
  @Transient
  public String modeAsString;
  
  @Transient
  public String amountAsString;
  
  @Transient
  public String dateAsString;
  
  public static final String FIELD_MODE = "mode";
  public static final String FIELD_MODE_AS_STRING = "modeAsString";
  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";
  public static final String FIELD_DATE_AS_STRING = "dateAsString";
  
  public static final String ENTITY_NAME = "Payment";
  public static final String TABLE_NAME = "TA_PAIEMENT";
  
  public static final String COLUMN_MODE = "MODE";
  public static final String COLUMN_AMOUNT = "MONTANT";
}
