package org.cyk.system.poulsscolaire.server.impl.business.adjustedfee;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeService.AdjustedFeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineValidator;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeValidator;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.Fee;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;

/**
 * Cette classe représente la création d'un {@link AdjustedFee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeeCreateBusiness extends AbstractIdentifiableCreateBusiness<AdjustedFee,
    AdjustedFeePersistence, AdjustedFeeValidator, AdjustedFeeCreateRequestDto> {

  @Inject
  @Getter
  AdjustedFeePersistence persistence;

  @Inject
  @Getter
  AdjustedFeeValidator validator;

  @Inject
  DeadlineValidator deadlineValidator;

  @Inject
  AmountPersistence amountPersistence;

  @Inject
  FeeValidator feeValidator;

  @Inject
  RegistrationValidator registrationValidator;

  @Override
  protected Object[] validate(AdjustedFeeCreateRequestDto request, StringList messages) {
    Fee fee = feeValidator.validateInstanceByIdentifier(request.getFeeIdentifier(), messages);
    Registration registration = registrationValidator
        .validateInstanceByIdentifier(request.getRegistrationIdentifier(), messages);
    Deadline deadline =
        deadlineValidator.validateInstanceByIdentifier(request.getDeadlineIdentifier(), messages);
    return new Object[] {deadline, fee, registration};
  }

  @Override
  protected void setFields(AdjustedFee adjustedFee, Object[] array,
      AdjustedFeeCreateRequestDto request) {
    super.setFields(adjustedFee, array, request);
    adjustedFee.amount = new Amount();
    adjustedFee.amount.generateIdentifier();
    adjustedFee.amount.audit = adjustedFee.audit;
    adjustedFee.amount.set(request, array);
    adjustedFee.fee = (Fee) array[1];
    adjustedFee.registration = (Registration) array[2];
  }

  @Override
  protected void doTransact(AdjustedFee adjustedFee) {
    amountPersistence.create(adjustedFee.amount);
    super.doTransact(adjustedFee);
  }
}
