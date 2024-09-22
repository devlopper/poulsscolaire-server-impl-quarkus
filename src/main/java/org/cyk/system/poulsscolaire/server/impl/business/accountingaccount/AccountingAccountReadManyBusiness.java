package org.cyk.system.poulsscolaire.server.impl.business.accountingaccount;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountService.AccountingAccountGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountPersistence;

/**
 * Cette classe représente l'obtention de {@link AccountingAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<AccountingAccount, AccountingAccountPersistence,
        AccountingAccountDynamicQuery, AccountingAccountDto, AccountingAccountMapper,
        AccountingAccountGetManyResponseDto> {

  protected AccountingAccountReadManyBusiness() {
    super(AccountingAccountGetManyResponseDto.class);
  }

  @Inject
  @Getter
  AccountingAccountPersistence persistence;

  @Inject
  @Getter
  AccountingAccountDynamicQuery dynamicQuery;

  @Inject
  @Getter
  AccountingAccountMapper mapper;
}
