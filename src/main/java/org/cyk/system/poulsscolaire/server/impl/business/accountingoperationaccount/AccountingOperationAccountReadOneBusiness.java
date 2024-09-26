package org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountDto;
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
public class AccountingOperationAccountReadOneBusiness extends
    AbstractIdentifiableReadOneBusiness<AccountingOperationAccount,
        AccountingOperationAccountPersistence, AccountingOperationAccountDynamicQuery,
        AccountingOperationAccountDto, AccountingOperationAccountMapper> {

  protected AccountingOperationAccountReadOneBusiness() {
    super(AccountingOperationAccountDto.class);
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
