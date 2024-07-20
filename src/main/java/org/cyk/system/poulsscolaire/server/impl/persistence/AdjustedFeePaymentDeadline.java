package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableAuditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

/**
 * Cette classe représente une échéance de paiement de frais ajusté.
 *
 * @author Christian
 *
 */
@Entity(name = AdjustedFeePaymentDeadline.ENTITY_NAME)
@Table(name = AdjustedFeePaymentDeadline.TABLE_NAME)
public class AdjustedFeePaymentDeadline extends AbstractIdentifiableAuditable {

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_ADJUSTED_FEE, nullable = false)
  public AdjustedFee adjustedFee;

  @NotNull
  @ManyToOne
  @JoinColumn(name = COLUMN_DEADLINE, nullable = false)
  public Deadline deadline;
  
  @NotNull
  @Column(name = COLUMN_AMOUNT, nullable = false)
  public Integer amount;

  /* valeurs dérivées */

  @Transient
  public String adjustedFeeIdentifier;
  
  @Transient
  public String adjustedFeeAsString;

  @Transient
  public String deadlineIdentifier;

  @Transient
  public String deadlineAsString;

  @Transient
  public String amountAsString;
  
  public static final String FIELD_ADJUSTED_FEE = "adjustedFee";
  public static final String FIELD_ADJUSTED_FEE_AS_STRING = "adjustedFeeAsString";
  public static final String FIELD_ADJUSTED_FEE_IDENTIFIER = "adjustedFeeIdentifier";
  public static final String FIELD_DEADLINE = "deadline";
  public static final String FIELD_DEADLINE_IDENTIFIER = "deadlineIdentifier";
  public static final String FIELD_DEADLINE_AS_STRING = "deadlineAsString";
  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_AS_STRING = "amountAsString";
  
  public static final String ENTITY_NAME = "AdjustedFeePaymentDeadline";
  public static final String TABLE_NAME = "TA_ECHEANCE_PAIEMENT_FRAIS_AJUSTE";

  public static final String COLUMN_ADJUSTED_FEE = "FRAIS_AJUSTE";
  public static final String COLUMN_DEADLINE = "ECHEANCE";
  public static final String COLUMN_AMOUNT = "MONTANT";
}
