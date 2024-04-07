package org.cyk.system.poulsscolaire.server.impl.business.amount;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.AmountService.AmountCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;

/**
 * Cette classe représente la création d'un {@link Amount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountCreateBusiness extends AbstractIdentifiableCreateBusiness<Amount,
    AmountPersistence, AmountValidator, AmountCreateRequestDto> {

  @Inject
  @Getter
  AmountPersistence persistence;

  @Inject
  @Getter
  AmountValidator validator;

  @Inject
  DeadlineValidator deadlineValidator;

  @Override
  protected Object[] validate(AmountCreateRequestDto request, StringList messages) {
    Deadline deadline =
        deadlineValidator.validateInstanceByIdentifier(request.getDeadlineIdentifier(), messages);
    return new Object[] {deadline};
  }

  @Override
  protected void setFields(Amount amount, Object[] array, AmountCreateRequestDto request) {
    super.setFields(amount, array, request);
    amount.deadline = (Deadline) array[0];
    amount.optional = request.getOptional();
    amount.paymentOrderNumber = request.getPaymentOrderNumber();
    amount.registrationValuePart = request.getRegistrationValuePart();
    amount.renewable = request.getRenewable();
    amount.value = request.getValue();
  }
}
