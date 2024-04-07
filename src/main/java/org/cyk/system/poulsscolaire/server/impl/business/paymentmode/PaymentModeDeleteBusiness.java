package org.cyk.system.poulsscolaire.server.impl.business.paymentmode;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentMode;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentModePersistence;

/**
 * Cette classe représente la suppression de {@link PaymentMode}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentModeDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    PaymentMode, PaymentModePersistence, PaymentModeValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  PaymentModePersistence persistence;

  @Inject
  @Getter
  PaymentModeValidator validator;
}
