package org.cyk.system.poulsscolaire.server.impl.business.cashregister;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterService.CashRegisterCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegister;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegisterPersistence;

/**
 * Cette classe représente la création d'un {@link CashRegister}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class CashRegisterCreateBusiness extends AbstractIdentifiableCreateBusiness<CashRegister,
    CashRegisterPersistence, CashRegisterValidator, CashRegisterCreateRequestDto> {

  @Inject
  @Getter
  CashRegisterPersistence persistence;

  @Inject
  @Getter
  CashRegisterValidator validator;
}
