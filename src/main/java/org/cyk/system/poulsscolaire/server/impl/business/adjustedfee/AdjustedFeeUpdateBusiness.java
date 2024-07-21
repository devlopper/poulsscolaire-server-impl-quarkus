package org.cyk.system.poulsscolaire.server.impl.business.adjustedfee;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeService.AdjustedFeeUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineValidator;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeValidator;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;

/**
 * Cette classe représente la mise à jour de {@link AdjustedFee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeeUpdateBusiness extends AbstractIdentifiableUpdateBusiness<AdjustedFee,
    AdjustedFeePersistence, AdjustedFeeValidator, AdjustedFeeUpdateRequestDto> {

  @Inject
  @Getter
  AdjustedFeePersistence persistence;

  @Inject
  @Getter
  AdjustedFeeValidator validator;

  @Inject
  DeadlineValidator deadlineValidator;

  @Inject
  FeeValidator feeValidator;

  @Inject
  RegistrationValidator registrationValidator;

  @Inject
  AmountPersistence amountPersistence;

  @Override
  protected void validate(AdjustedFeeUpdateRequestDto request, StringList messages,
      AdjustedFee adjustedFee) {
    super.validate(request, messages, adjustedFee);
    adjustedFee.fee =
        feeValidator.validateInstanceByIdentifier(request.getFeeIdentifier(), messages);
    adjustedFee.registration = registrationValidator
        .validateInstanceByIdentifier(request.getRegistrationIdentifier(), messages);
  }

  @Override
  protected void prepare(AdjustedFee adjustedFee, AdjustedFeeUpdateRequestDto request) {
    super.prepare(adjustedFee, request);
    adjustedFee.amount.set(request, null);
  }

  @Override
  protected void doTransact(AdjustedFee adjustedFee) {
    amountPersistence.update(adjustedFee.amount);
    super.doTransact(adjustedFee);
  }
}
