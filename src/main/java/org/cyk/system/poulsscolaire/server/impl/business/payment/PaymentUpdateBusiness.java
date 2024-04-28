package org.cyk.system.poulsscolaire.server.impl.business.payment;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentService.PaymentUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentPersistence;

/**
 * Cette classe représente la mise à jour de {@link Payment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentUpdateBusiness extends AbstractIdentifiableUpdateBusiness<Payment,
    PaymentPersistence, PaymentValidator, PaymentUpdateRequestDto> {

  @Inject
  @Getter
  PaymentPersistence persistence;

  @Inject
  @Getter
  PaymentValidator validator;
}
