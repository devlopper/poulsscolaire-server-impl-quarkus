package org.cyk.system.poulsscolaire.server.impl.business.amountdeadline;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineService.AmountDeadlineUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountValidator;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadlinePersistence;

/**
 * Cette classe représente la mise à jour de {@link AmountDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountDeadlineUpdateBusiness extends AbstractIdentifiableUpdateBusiness<AmountDeadline,
    AmountDeadlinePersistence, AmountDeadlineValidator, AmountDeadlineUpdateRequestDto> {

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
  protected void validate(AmountDeadlineUpdateRequestDto request, StringList messages,
      AmountDeadline adjustedFeePaymentDeadline) {
    super.validate(request, messages, adjustedFeePaymentDeadline);
    adjustedFeePaymentDeadline.amount =
        amountValidator.validateInstanceByIdentifier(request.getAmountIdentifier(), messages);
    adjustedFeePaymentDeadline.deadline =
        deadlineValidator.validateInstanceByIdentifier(request.getDeadlineIdentifier(), messages);
  }

  @Override
  protected void prepare(AmountDeadline adjustedFeePaymentDeadline,
      AmountDeadlineUpdateRequestDto request) {
    super.prepare(adjustedFeePaymentDeadline, request);
    adjustedFeePaymentDeadline.payment = request.getPayment();
  }
}
