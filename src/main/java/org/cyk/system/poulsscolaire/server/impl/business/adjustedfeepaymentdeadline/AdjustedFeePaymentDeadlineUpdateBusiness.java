package org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineService.AdjustedFeePaymentDeadlineUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeValidator;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePaymentDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePaymentDeadlinePersistence;

/**
 * Cette classe représente la mise à jour de {@link AdjustedFeePaymentDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeePaymentDeadlineUpdateBusiness extends
    AbstractIdentifiableUpdateBusiness<AdjustedFeePaymentDeadline,
        AdjustedFeePaymentDeadlinePersistence, AdjustedFeePaymentDeadlineValidator,
        AdjustedFeePaymentDeadlineUpdateRequestDto> {

  @Inject
  @Getter
  AdjustedFeePaymentDeadlinePersistence persistence;

  @Inject
  @Getter
  AdjustedFeePaymentDeadlineValidator validator;

  @Inject
  AdjustedFeeValidator adjustedFeeValidator;

  @Inject
  DeadlineValidator deadlineValidator;

  @Override
  protected void validate(AdjustedFeePaymentDeadlineUpdateRequestDto request, StringList messages,
      AdjustedFeePaymentDeadline adjustedFeePaymentDeadline) {
    super.validate(request, messages, adjustedFeePaymentDeadline);
    adjustedFeePaymentDeadline.adjustedFee = adjustedFeeValidator
        .validateInstanceByIdentifier(request.getAdjustedFeeIdentifier(), messages);
    adjustedFeePaymentDeadline.deadline =
        deadlineValidator.validateInstanceByIdentifier(request.getDeadlineIdentifier(), messages);
  }

  @Override
  protected void prepare(AdjustedFeePaymentDeadline adjustedFeePaymentDeadline,
      AdjustedFeePaymentDeadlineUpdateRequestDto request) {
    super.prepare(adjustedFeePaymentDeadline, request);
    adjustedFeePaymentDeadline.amount = request.getAmount();
  }
}
