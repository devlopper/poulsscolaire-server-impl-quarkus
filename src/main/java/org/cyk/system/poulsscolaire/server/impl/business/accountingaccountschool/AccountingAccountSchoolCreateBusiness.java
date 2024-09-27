package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolService.AccountingAccountSchoolCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchool;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchoolPersistence;

/**
 * Cette classe représente la création de {@link AccountingAccountSchool}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountSchoolCreateBusiness extends
    AbstractIdentifiableCreateBusiness<AccountingAccountSchool, AccountingAccountSchoolPersistence,
        AccountingAccountSchoolValidator, AccountingAccountSchoolCreateRequestDto> {

  @Inject
  @Getter
  AccountingAccountSchoolPersistence persistence;

  @Inject
  @Getter
  AccountingAccountSchoolValidator validator;

  @Inject
  AccountingAccountValidator accountValidator;

  @Override
  protected Object[] validate(AccountingAccountSchoolCreateRequestDto request,
      StringList messages) {
    AccountingAccount account =
        accountValidator.validateInstanceByIdentifier(request.getAccountIdentifier(), messages);
    return new Object[] {account};
  }

  @Override
  protected void setFields(AccountingAccountSchool accountingAccountSchool, Object[] array,
      AccountingAccountSchoolCreateRequestDto request) {
    super.setFields(accountingAccountSchool, array, request);
    accountingAccountSchool.account = (AccountingAccount) array[0];
    accountingAccountSchool.schoolIdentifier = request.getSchoolIdentifier();
  }
}
