package org.cyk.system.poulsscolaire.server.impl.business.paymentmode;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.PaymentModeService.PaymentModeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentMode;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentModePersistence;

/**
 * Cette classe représente la création d'un {@link PaymentMode}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentModeCreateBusiness extends AbstractIdentifiableCreateBusiness<PaymentMode,
    PaymentModePersistence, PaymentModeValidator, PaymentModeCreateRequestDto> {

  @Inject
  @Getter
  PaymentModePersistence persistence;

  @Inject
  @Getter
  PaymentModeValidator validator;
}
