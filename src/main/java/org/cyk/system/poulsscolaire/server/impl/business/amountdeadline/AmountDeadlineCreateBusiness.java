package org.cyk.system.poulsscolaire.server.impl.business.amountdeadline;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineService.AmountDeadlineCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountValidator;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadlinePersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;

/**
 * Cette classe représente la création d'un {@link AmountDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountDeadlineCreateBusiness extends AbstractIdentifiableCreateBusiness<AmountDeadline,
    AmountDeadlinePersistence, AmountDeadlineValidator, AmountDeadlineCreateRequestDto> {

  @Inject
  @Getter
  AmountDeadlinePersistence persistence;

  @Inject
  @Getter
  AmountDeadlineValidator validator;

  @Inject
  AmountValidator amountValidator;

  @Inject
  DeadlineValidator deadlineValidator;

  @Override
  protected Object[] validate(AmountDeadlineCreateRequestDto request, StringList messages) {
    Amount amount =
        amountValidator.validateInstanceByIdentifier(request.getAmountIdentifier(), messages);
    Deadline deadline =
        deadlineValidator.validateInstanceByIdentifier(request.getDeadlineIdentifier(), messages);
    Core.runIfNotNull(amount,
        () -> validator.validatePayment(persistence.getPaymentSumByAmount(amount),
            request.getPayment(), amount.value, messages));
    return new Object[] {amount, deadline};
  }

  @Override
  protected void setFields(AmountDeadline adjustedFeePaymentDeadline, Object[] array,
      AmountDeadlineCreateRequestDto request) {
    super.setFields(adjustedFeePaymentDeadline, array, request);
    adjustedFeePaymentDeadline.amount = (Amount) array[0];
    adjustedFeePaymentDeadline.deadline = (Deadline) array[1];
    adjustedFeePaymentDeadline.payment = request.getPayment();
  }
}
