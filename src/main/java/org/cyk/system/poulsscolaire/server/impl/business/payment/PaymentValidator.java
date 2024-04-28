package org.cyk.system.poulsscolaire.server.impl.business.payment;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentPersistence;

/**
 * Cette class représente un validateur de {@link Payment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentValidator
    extends AbstractIdentifiableCodableValidator<Payment> {

  @Inject
  @Getter
  private PaymentPersistence persistence;

}
