package org.cyk.system.poulsscolaire.server.impl.business.cashregister;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegister;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegisterDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegisterPersistence;

/**
 * Cette classe représente l'obtention de {@link CashRegister}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class CashRegisterReadOneBusiness extends AbstractIdentifiableReadOneBusiness<CashRegister,
    CashRegisterPersistence, CashRegisterDynamicQuery, CashRegisterDto, CashRegisterMapper> {

  protected CashRegisterReadOneBusiness() {
    super(CashRegisterDto.class);
  }

  @Inject
  @Getter
  CashRegisterPersistence persistence;

  @Inject
  @Getter
  CashRegisterDynamicQuery dynamicQuery;

  @Inject
  @Getter
  CashRegisterMapper mapper;
}
