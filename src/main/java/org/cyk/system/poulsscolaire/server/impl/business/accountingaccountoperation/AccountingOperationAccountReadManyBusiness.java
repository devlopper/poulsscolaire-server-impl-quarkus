package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountService.AccountingOperationAccountGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccountDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccountPersistence;

/**
 * Cette classe représente l'obtention de {@link AccountingOperationAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationAccountReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<AccountingOperationAccount,
        AccountingOperationAccountPersistence, AccountingOperationAccountDynamicQuery,
        AccountingOperationAccountDto, AccountingOperationAccountMapper,
        AccountingOperationAccountGetManyResponseDto> {

  protected AccountingOperationAccountReadManyBusiness() {
    super(AccountingOperationAccountGetManyResponseDto.class);
  }

  @Inject
  @Getter
  AccountingOperationAccountPersistence persistence;

  @Inject
  @Getter
  AccountingOperationAccountDynamicQuery dynamicQuery;

  @Inject
  @Getter
  AccountingOperationAccountMapper mapper;
}
