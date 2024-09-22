package org.cyk.system.poulsscolaire.server.impl.business.accountingoperation;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperation;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link AccountingOperation} et
 * {@link AccountingOperationDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface AccountingOperationMapper
    extends IdentifiableMapper<AccountingOperation, AccountingOperationDto> {

}
