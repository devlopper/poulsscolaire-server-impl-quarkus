package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamableAuditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Cette classe représente un mode de paiement.
 *
 * @author Christian
 *
 */
@Entity(name = PaymentMode.ENTITY_NAME)
@Table(name = PaymentMode.TABLE_NAME)
public class PaymentMode extends AbstractIdentifiableCodableNamableAuditable {

  public static final String ENTITY_NAME = "PaymentMode";
  public static final String TABLE_NAME = "TA_MODE_PAIEMENT";
  
}
