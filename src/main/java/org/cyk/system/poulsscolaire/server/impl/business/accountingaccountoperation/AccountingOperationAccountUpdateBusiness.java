package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountService.AccountingOperationAccountUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountValidator;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccountPersistence;

/**
 * Cette classe représente la mise à jour de {@link AccountingOperationAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationAccountUpdateBusiness extends
    AbstractIdentifiableUpdateBusiness<AccountingOperationAccount,
        AccountingOperationAccountPersistence, AccountingOperationAccountValidator,
        AccountingOperationAccountUpdateRequestDto> {

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
  protected void validate(AccountingOperationAccountUpdateRequestDto request, StringList messages,
      AccountingOperationAccount accountingOperationAccount) {
    super.validate(request, messages, accountingOperationAccount);
    accountingOperationAccount.operation =
        operationValidator.validateInstanceByIdentifier(request.getOperationIdentifier(), messages);
    accountingOperationAccount.account =
        accountValidator.validateInstanceByIdentifier(request.getAccountIdentifier(), messages);
    accountingOperationAccount.amount = request.getAmount();
  }
}
