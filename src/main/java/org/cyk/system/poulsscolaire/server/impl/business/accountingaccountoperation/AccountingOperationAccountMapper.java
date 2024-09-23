package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccount;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link AccountingOperationAccount} et
 * {@link AccountingOperationAccountDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface AccountingOperationAccountMapper
    extends IdentifiableMapper<AccountingOperationAccount, AccountingOperationAccountDto> {

}
