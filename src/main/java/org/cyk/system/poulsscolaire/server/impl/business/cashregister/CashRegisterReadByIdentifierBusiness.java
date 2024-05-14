package org.cyk.system.poulsscolaire.server.impl.business.cashregister;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegister;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegisterDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegisterPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link CashRegister}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class CashRegisterReadByIdentifierBusiness
    extends AbstractIdentifiableReadByIdentifierBusiness<CashRegister, CashRegisterPersistence,
        CashRegisterDynamicQuery, CashRegisterDto, CashRegisterMapper> {

  protected CashRegisterReadByIdentifierBusiness() {
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
