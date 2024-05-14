package org.cyk.system.poulsscolaire.server.impl.business.cashregister;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegister;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegisterPersistence;

/**
 * Cette classe représente la suppression de {@link CashRegister}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class CashRegisterDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    CashRegister, CashRegisterPersistence, CashRegisterValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  CashRegisterPersistence persistence;

  @Inject
  @Getter
  CashRegisterValidator validator;
}
