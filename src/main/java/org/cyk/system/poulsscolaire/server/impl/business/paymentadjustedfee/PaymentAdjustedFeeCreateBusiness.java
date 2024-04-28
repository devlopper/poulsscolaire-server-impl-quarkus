package org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeService.PaymentAdjustedFeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeValidator;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFeePersistence;

/**
 * Cette classe représente la création de {@link PaymentAdjustedFee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentAdjustedFeeCreateBusiness
    extends AbstractIdentifiableCreateBusiness<PaymentAdjustedFee, PaymentAdjustedFeePersistence,
        PaymentAdjustedFeeValidator, PaymentAdjustedFeeCreateRequestDto> {

  @Inject
  @Getter
  PaymentAdjustedFeePersistence persistence;

  @Inject
  @Getter
  PaymentAdjustedFeeValidator validator;

  @Inject
  PaymentValidator paymentValidator;

  @Inject
  AdjustedFeeValidator adjustedFeeValidator;

  @Override
  protected Object[] validate(PaymentAdjustedFeeCreateRequestDto request, StringList messages) {
    Payment payment =
        paymentValidator.validateInstanceByIdentifier(request.getPaymentIdentifier(), messages);
    AdjustedFee adjustedFee = adjustedFeeValidator
        .validateInstanceByIdentifier(request.getAdjustedFeeIdentifier(), messages);
    return new Object[] {payment, adjustedFee};
  }

  @Override
  protected void setFields(PaymentAdjustedFee paymentAdjustedFee, Object[] array,
      PaymentAdjustedFeeCreateRequestDto request) {
    super.setFields(paymentAdjustedFee, array, request);
    paymentAdjustedFee.payment = (Payment) array[0];
    paymentAdjustedFee.adjustedFee = (AdjustedFee) array[1];
    paymentAdjustedFee.amount = request.getAmount();
  }
}
