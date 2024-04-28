package org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFeeDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFeePersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link PaymentAdjustedFee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentAdjustedFeeReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<PaymentAdjustedFee, PaymentAdjustedFeePersistence,
        PaymentAdjustedFeeDynamicQuery, PaymentAdjustedFeeDto, PaymentAdjustedFeeMapper> {

  protected PaymentAdjustedFeeReadByIdentifierBusiness() {
    super(PaymentAdjustedFeeDto.class);
  }

  @Inject
  @Getter
  PaymentAdjustedFeePersistence persistence;

  @Inject
  @Getter
  PaymentAdjustedFeeDynamicQuery dynamicQuery;

  @Inject
  @Getter
  PaymentAdjustedFeeMapper mapper;
}
