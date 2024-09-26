package org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccountDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccountPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link AccountingOperationAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationAccountReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<AccountingOperationAccount,
        AccountingOperationAccountPersistence, AccountingOperationAccountDynamicQuery,
        AccountingOperationAccountDto, AccountingOperationAccountMapper> {

  protected AccountingOperationAccountReadByIdentifierBusiness() {
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
