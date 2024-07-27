package org.cyk.system.poulsscolaire.server.impl.business.payment;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentPersistence;

/**
 * Cette classe représente l'annulation de {@link Payment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentCancelBusiness extends AbstractIdentifiableUpdateBusiness<Payment,
    PaymentPersistence, PaymentValidator, ByIdentifierRequestDto> {

  @Inject
  @Getter
  PaymentPersistence persistence;

  @Inject
  @Getter
  PaymentValidator validator;

  @Override
  protected void validate(ByIdentifierRequestDto request, StringList messages, Payment payment) {
    super.validate(request, messages, payment);
    validator.validateCanceled(payment.canceled, messages);
  }

  @Override
  protected void prepare(Payment payment, ByIdentifierRequestDto request) {
    super.prepare(payment, request);
    payment.canceled = true;
  }
}
