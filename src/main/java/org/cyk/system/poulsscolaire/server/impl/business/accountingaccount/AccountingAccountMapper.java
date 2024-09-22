package org.cyk.system.poulsscolaire.server.impl.business.accountingaccount;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link AccountingAccount} et
 * {@link AccountingAccountDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface AccountingAccountMapper
    extends IdentifiableMapper<AccountingAccount, AccountingAccountDto> {

}
