package org.cyk.system.poulsscolaire.server.impl.business.paymentmode;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentMode;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentModePersistence;

/**
 * Cette class représente un validateur de {@link PaymentMode}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentModeValidator extends AbstractIdentifiableCodableNamableValidator<PaymentMode> {

  @Inject
  @Getter
  private PaymentModePersistence persistence;

}
