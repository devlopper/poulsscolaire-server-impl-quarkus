package org.cyk.system.poulsscolaire.server.impl.business.accountingaccount;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountService.AccountingAccountCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlan;

/**
 * Cette classe représente la création de {@link AccountingAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountCreateBusiness
    extends AbstractIdentifiableCreateBusiness<AccountingAccount, AccountingAccountPersistence,
        AccountingAccountValidator, AccountingAccountCreateRequestDto> {

  @Inject
  @Getter
  AccountingAccountPersistence persistence;

  @Inject
  @Getter
  AccountingAccountValidator validator;

  @Inject
  AccountingPlanValidator planValidator;

  @Override
  protected Object[] validate(AccountingAccountCreateRequestDto request, StringList messages) {
    AccountingPlan plan =
        planValidator.validateInstanceByIdentifier(request.getPlanIdentifier(), messages);
    validator.validateType(request.getType(), messages);
    return new Object[] {plan};
  }

  @Override
  protected void setFields(AccountingAccount accountingAccount, Object[] array,
      AccountingAccountCreateRequestDto request) {
    super.setFields(accountingAccount, array, request);
    accountingAccount.plan = (AccountingPlan) array[0];
    accountingAccount.type = request.getType();
  }
}
