package org.cyk.system.poulsscolaire.server.impl.business.payment;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentService.PaymentCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeValidator;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFeePersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentMode;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;

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
  RegistrationValidator registrationValidator;

  @Inject
  PaymentModeValidator modeValidator;

  @Inject
  AdjustedFeePersistence adjustedFeePersistence;

  @Inject
  PaymentAdjustedFeePersistence paymentAdjustedFeePersistence;

  @Override
  protected Object[] validate(PaymentCreateRequestDto request, StringList messages) {
    Registration registration = registrationValidator
        .validateInstanceByIdentifier(request.getRegistrationIdentifier(), messages);
    PaymentMode mode =
        modeValidator.validateInstanceByIdentifier(request.getModeIdentifier(), messages);
    validationHelper.validateLowerThanByName(this, Integer.valueOf(request.getAmount()).longValue(),
        0L, "montant", "zéro", messages);
    List<Object[]> payables = null;
    if (registration != null) {
      payables = adjustedFeePersistence.getForPaymentByRegistration(registration);
      if (!messages.addIfCollectionEmpty(payables, "Aucun frais à payer")) {
        validationHelper.validateGreaterThanByName(this, request.getAmount(),
            payables.stream().mapToLong(array -> (Long) array[2]).sum(), "montant", "reste à payer",
            messages);
        messages.addIfTrue(
            request.getAmount() > payables.stream().mapToLong(array -> (Long) array[2]).sum(),
            "Le montant est élevé");
      }
    }
    return new Object[] {registration, mode, payables};
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void setFields(Payment payment, Object[] array, PaymentCreateRequestDto request) {
    super.setFields(payment, array, request);
    payment.registration = (Registration) array[0];
    payment.mode = (PaymentMode) array[1];
    payment.code = String.format("P%s", System.currentTimeMillis());
    payment.amount = request.getAmount();
    payment.payables = (List<Object[]>) array[2];
  }

  @Override
  protected void doTransact(Payment payment) {
    super.doTransact(payment);
    AtomicInteger amount = new AtomicInteger(payment.amount);
    Collection<PaymentAdjustedFee> paymentAdjustedFees = payment.payables.stream().map(array -> {
      PaymentAdjustedFee paymentAdjustedFee = new PaymentAdjustedFee();
      paymentAdjustedFee.generateIdentifier();
      paymentAdjustedFee.audit = payment.audit;
      paymentAdjustedFee.payment = payment;
      paymentAdjustedFee.adjustedFee = (AdjustedFee) array[0];
      if (amount.get() >= paymentAdjustedFee.adjustedFee.amount.value) {
        paymentAdjustedFee.amount = paymentAdjustedFee.adjustedFee.amount.value;
      } else {
        paymentAdjustedFee.amount = amount.get();
      }
      amount.addAndGet(-paymentAdjustedFee.amount);
      return paymentAdjustedFee;
    }).toList();
    paymentAdjustedFeePersistence.create(paymentAdjustedFees);
  }
}
