package org.cyk.system.poulsscolaire.server.impl.business.cashregister;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegister;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link CashRegister} et {@link CashRegisterDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface CashRegisterMapper extends IdentifiableMapper<CashRegister, CashRegisterDto> {

}
