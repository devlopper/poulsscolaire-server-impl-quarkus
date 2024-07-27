package org.cyk.system.poulsscolaire.server.impl.business.paymentmode;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentModeDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentModeService.PaymentModeGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentMode;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentModeDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentModePersistence;

/**
 * Cette classe représente l'obtention de {@link PaymentMode}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentModeReadManyBusiness
    extends AbstractIdentifiableReadManyBusiness<PaymentMode, PaymentModePersistence,
        PaymentModeDynamicQuery, PaymentModeDto, PaymentModeMapper, PaymentModeGetManyResponseDto> {

  protected PaymentModeReadManyBusiness() {
    super(PaymentModeGetManyResponseDto.class);
  }

  @Inject
  @Getter
  PaymentModePersistence persistence;

  @Inject
  @Getter
  PaymentModeDynamicQuery dynamicQuery;

  @Inject
  @Getter
  PaymentModeMapper mapper;
}
