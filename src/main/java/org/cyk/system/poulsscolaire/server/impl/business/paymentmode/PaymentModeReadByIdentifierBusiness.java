package org.cyk.system.poulsscolaire.server.impl.business.paymentmode;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.PaymentModeDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentMode;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentModeDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentModePersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link PaymentMode}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentModeReadByIdentifierBusiness
    extends AbstractIdentifiableReadByIdentifierBusiness<PaymentMode, PaymentModePersistence,
        PaymentModeDynamicQuery, PaymentModeDto, PaymentModeMapper> {

  protected PaymentModeReadByIdentifierBusiness() {
    super(PaymentModeDto.class);
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
