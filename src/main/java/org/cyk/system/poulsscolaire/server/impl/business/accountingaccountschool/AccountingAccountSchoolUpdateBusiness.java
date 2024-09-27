package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolService.AccountingAccountSchoolUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchool;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchoolPersistence;

/**
 * Cette classe représente la mise à jour de {@link AccountingAccountSchool}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountSchoolUpdateBusiness extends
    AbstractIdentifiableUpdateBusiness<AccountingAccountSchool, AccountingAccountSchoolPersistence,
        AccountingAccountSchoolValidator, AccountingAccountSchoolUpdateRequestDto> {

  @Inject
  @Getter
  AccountingAccountSchoolPersistence persistence;

  @Inject
  @Getter
  AccountingAccountSchoolValidator validator;

  @Inject
  AccountingAccountValidator accountValidator;

  @Override
  protected void validate(AccountingAccountSchoolUpdateRequestDto request, StringList messages,
      AccountingAccountSchool accountingAccountSchool) {
    super.validate(request, messages, accountingAccountSchool);
    accountingAccountSchool.account =
        accountValidator.validateInstanceByIdentifier(request.getAccountIdentifier(), messages);
  }

  @Override
  protected void prepare(AccountingAccountSchool accountingAccountSchool,
      AccountingAccountSchoolUpdateRequestDto request) {
    super.prepare(accountingAccountSchool, request);
    accountingAccountSchool.schoolIdentifier = request.getSchoolIdentifier();
  }
}
