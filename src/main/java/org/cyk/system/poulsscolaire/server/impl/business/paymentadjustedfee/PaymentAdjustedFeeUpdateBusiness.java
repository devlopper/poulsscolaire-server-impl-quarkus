package org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeService.PaymentAdjustedFeeUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFeePersistence;

/**
 * Cette classe représente la mise à jour de {@link PaymentAdjustedFee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentAdjustedFeeUpdateBusiness
    extends AbstractIdentifiableUpdateBusiness<PaymentAdjustedFee, PaymentAdjustedFeePersistence,
        PaymentAdjustedFeeValidator, PaymentAdjustedFeeUpdateRequestDto> {

  @Inject
  @Getter
  PaymentAdjustedFeePersistence persistence;

  @Inject
  @Getter
  PaymentAdjustedFeeValidator validator;
}
