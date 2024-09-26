package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountService.AccountingOperationAccountCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountValidator;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperation;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccountPersistence;

/**
 * Cette classe représente la création de {@link AccountingOperationAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationAccountCreateBusiness extends
    AbstractIdentifiableCreateBusiness<AccountingOperationAccount,
        AccountingOperationAccountPersistence, AccountingOperationAccountValidator,
        AccountingOperationAccountCreateRequestDto> {

  @Inject
  @Getter
  AccountingOperationAccountPersistence persistence;

  @Inject
  @Getter
  AccountingOperationAccountValidator validator;

  @Inject
  AccountingOperationValidator operationValidator;

  @Inject
  AccountingAccountValidator accountValidator;

  @Override
  protected Object[] validate(AccountingOperationAccountCreateRequestDto request,
      StringList messages) {
    AccountingOperation operation =
        operationValidator.validateInstanceByIdentifier(request.getOperationIdentifier(), messages);
    AccountingAccount account =
        accountValidator.validateInstanceByIdentifier(request.getAccountIdentifier(), messages);
    validator.validateAmount(request.getAmount(), messages);
    return new Object[] {operation, account};
  }

  @Override
  protected void setFields(AccountingOperationAccount accountingOperationAccount, Object[] array,
      AccountingOperationAccountCreateRequestDto request) {
    super.setFields(accountingOperationAccount, array, request);
    accountingOperationAccount.operation = (AccountingOperation) array[0];
    accountingOperationAccount.account = (AccountingAccount) array[1];
    accountingOperationAccount.amount = request.getAmount();
    accountingOperationAccount.setCode(computeCode(accountingOperationAccount));
    accountingOperationAccount.setName(computeName(accountingOperationAccount));
  }

  String computeCode(AccountingOperationAccount accountingOperationAccount) {
    return "CO" + accountingOperationAccount.operation.schoolIdentifier + persistence.countAll();
  }

  String computeName(AccountingOperationAccount accountingOperationAccount) {
    return Core.getOrDefaultIfBlank(accountingOperationAccount.name,
        accountingOperationAccount.account.name);
  }
}
