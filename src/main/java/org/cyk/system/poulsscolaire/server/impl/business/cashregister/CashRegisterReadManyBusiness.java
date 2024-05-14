package org.cyk.system.poulsscolaire.server.impl.business.cashregister;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterDto;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterService.GetManyResponseDto;
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
public class CashRegisterReadManyBusiness
    extends AbstractIdentifiableReadManyBusiness<CashRegister, CashRegisterPersistence,
        CashRegisterDynamicQuery, CashRegisterDto, CashRegisterMapper, GetManyResponseDto> {

  protected CashRegisterReadManyBusiness() {
    super(GetManyResponseDto.class);
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
