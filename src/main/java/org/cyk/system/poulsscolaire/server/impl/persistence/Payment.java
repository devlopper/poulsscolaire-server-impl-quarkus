package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import java.util.List;

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
  @JoinColumn(name = COLUMN_REGISTRATION, nullable = false)
  public Registration registration;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_MODE, nullable = false)
  public PaymentMode mode;

  @Column(name = COLUMN_CANCELED)
  public Boolean canceled;
  
  /* Transients */

  @Transient
  public int amount;

  @Transient
  public String modeAsString;

  @Transient
  public String amountAsString;

  @Transient
  public String dateAsString;

  @Transient
  public List<Object[]> payables;

  public static final String FIELD_REGISTRATION = "registration";
  public static final String FIELD_MODE = "mode";
  public static final String FIELD_MODE_AS_STRING = "modeAsString";
  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";
  public static final String FIELD_DATE_AS_STRING = "dateAsString";

  public static final String ENTITY_NAME = "Payment";
  public static final String TABLE_NAME = "TA_PAIEMENT";

  public static final String COLUMN_REGISTRATION = "INSCRIPTION";
  public static final String COLUMN_MODE = "MODE";
  public static final String COLUMN_CANCELED = "ANNULE";
}
