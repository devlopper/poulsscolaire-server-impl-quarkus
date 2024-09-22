package org.cyk.system.poulsscolaire.server.impl.business.accountingaccount;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountService.AccountingAccountUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountPersistence;

/**
 * Cette classe représente la mise à jour de {@link AccountingAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountUpdateBusiness
    extends AbstractIdentifiableUpdateBusiness<AccountingAccount, AccountingAccountPersistence,
        AccountingAccountValidator, AccountingAccountUpdateRequestDto> {

  @Inject
  @Getter
  AccountingAccountPersistence persistence;

  @Inject
  @Getter
  AccountingAccountValidator validator;

  @Inject
  AccountingPlanValidator planValidator;

  @Override
  protected void validate(AccountingAccountUpdateRequestDto request, StringList messages,
      AccountingAccount accountingAccount) {
    super.validate(request, messages, accountingAccount);
    accountingAccount.plan =
        planValidator.validateInstanceByIdentifier(request.getPlanIdentifier(), messages);
    validator.validateType(request.getType(), messages);
    accountingAccount.type = request.getType();
  }
}
