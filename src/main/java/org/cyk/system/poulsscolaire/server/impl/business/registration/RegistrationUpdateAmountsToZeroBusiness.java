package org.cyk.system.poulsscolaire.server.impl.business.registration;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadlinePersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.RegistrationPersistence;

/**
 * Cette classe représente la mise à jour de montants à zéro {@link Registration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class RegistrationUpdateAmountsToZeroBusiness extends AbstractIdentifiableUpdateBusiness<
    Registration, RegistrationPersistence, RegistrationValidator, ByIdentifierRequestDto> {

  @Inject
  @Getter
  RegistrationPersistence persistence;

  @Inject
  @Getter
  RegistrationValidator validator;

  @Inject
  AmountPersistence amountPersistence;

  @Inject
  AmountDeadlinePersistence amountDeadlinePersistence;

  @Override
  protected void prepare(Registration registration, ByIdentifierRequestDto request) {
    super.prepare(registration, request);
    registration.preRegistrationAmount = 0;
  }

  @Override
  protected void doTransact(Registration registration) {
    super.doTransact(registration);
    List<Amount> amounts = amountPersistence.getWhereValueNotZeroByRegistration(registration);
    Core.runIfCollectionNotEmpty(amounts, () -> {
      amounts.forEach(amount -> {
        amount.value = 0;
        amount.registrationValuePart = null;
        amount.audit = registration.audit;
      });
      amountPersistence.update(amounts);
    });

    List<AmountDeadline> amountDeadlines =
        amountDeadlinePersistence.getWherePaymentNotZeroByRegistration(registration);
    Core.runIfCollectionNotEmpty(amountDeadlines, () -> {
      amountDeadlines.forEach(amountDeadline -> {
        amountDeadline.payment = 0;
        amountDeadline.audit = registration.audit;
      });
      amountDeadlinePersistence.update(amountDeadlines);
    });
  }
}
