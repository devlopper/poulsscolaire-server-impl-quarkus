package org.cyk.system.poulsscolaire.server.impl.business.paymentmode;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentModeService.PaymentModeUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentMode;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentModePersistence;

/**
 * Cette classe représente la mise à jour de {@link PaymentMode}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentModeUpdateBusiness extends AbstractIdentifiableUpdateBusiness<PaymentMode,
    PaymentModePersistence, PaymentModeValidator, PaymentModeUpdateRequestDto> {

  @Inject
  @Getter
  PaymentModePersistence persistence;

  @Inject
  @Getter
  PaymentModeValidator validator;
}
