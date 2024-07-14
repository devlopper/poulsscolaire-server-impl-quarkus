package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * Cette classe représente la base de montant de {@link Registration}.
 *
 * @author Christian
 *
 */
@MappedSuperclass
public abstract class AbstractRegistrationAmount extends AbstractIdentifiable {

  @Column(name = "MONTANT")
  public long amount;

  @Column(name = "MONTANT_INSCRIPTION")
  public long amountRegistration;
  
  public static final String FIELD_AMOUNT = "amount";
  public static final String FIELD_AMOUNT_REGISTRATION = "amountRegistration";
}
