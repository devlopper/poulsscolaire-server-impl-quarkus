package org.cyk.system.poulsscolaire.server.impl.business.payment;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentService.PaymentCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentMode;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentPersistence;

/**
 * Cette classe représente la création de {@link Payment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentCreateBusiness extends AbstractIdentifiableCreateBusiness<Payment,
    PaymentPersistence, PaymentValidator, PaymentCreateRequestDto> {

  @Inject
  @Getter
  PaymentPersistence persistence;

  @Inject
  @Getter
  PaymentValidator validator;

  @Inject
  PaymentModeValidator modeValidator;

  @Override
  protected Object[] validate(PaymentCreateRequestDto request, StringList messages) {
    PaymentMode mode =
        modeValidator.validateInstanceByIdentifier(request.getModeIdentifier(), messages);
    return new Object[] {mode};
  }

  @Override
  protected void setFields(Payment payment, Object[] array,
      PaymentCreateRequestDto request) {
    super.setFields(payment, array, request);
    payment.mode = (PaymentMode) array[0];
    payment.amount = request.getAmount();
    payment.code = String.format("P%s", System.currentTimeMillis());
  }
}
