package org.cyk.system.poulsscolaire.server.impl.business.cashregister;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterService.CashRegisterUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegister;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegisterPersistence;

/**
 * Cette classe représente la mise à jour de {@link CashRegister}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class CashRegisterUpdateBusiness extends AbstractIdentifiableUpdateBusiness<CashRegister,
    CashRegisterPersistence, CashRegisterValidator, CashRegisterUpdateRequestDto> {

  @Inject
  @Getter
  CashRegisterPersistence persistence;

  @Inject
  @Getter
  CashRegisterValidator validator;
}
