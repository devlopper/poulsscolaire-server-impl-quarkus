package org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineService.AdjustedFeePaymentDeadlineCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeValidator;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePaymentDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePaymentDeadlinePersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;

/**
 * Cette classe représente la création d'un {@link AdjustedFeePaymentDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeePaymentDeadlineCreateBusiness extends
    AbstractIdentifiableCreateBusiness<AdjustedFeePaymentDeadline,
        AdjustedFeePaymentDeadlinePersistence, AdjustedFeePaymentDeadlineValidator,
        AdjustedFeePaymentDeadlineCreateRequestDto> {

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
  protected Object[] validate(AdjustedFeePaymentDeadlineCreateRequestDto request,
      StringList messages) {
    AdjustedFee adjustedFee = adjustedFeeValidator
        .validateInstanceByIdentifier(request.getAdjustedFeeIdentifier(), messages);
    Deadline deadline =
        deadlineValidator.validateInstanceByIdentifier(request.getDeadlineIdentifier(), messages);
    return new Object[] {adjustedFee, deadline};
  }

  @Override
  protected void setFields(AdjustedFeePaymentDeadline adjustedFeePaymentDeadline, Object[] array,
      AdjustedFeePaymentDeadlineCreateRequestDto request) {
    super.setFields(adjustedFeePaymentDeadline, array, request);
    adjustedFeePaymentDeadline.adjustedFee = (AdjustedFee) array[0];
    adjustedFeePaymentDeadline.deadline = (Deadline) array[1];
    adjustedFeePaymentDeadline.amount = request.getAmount();
  }
}
