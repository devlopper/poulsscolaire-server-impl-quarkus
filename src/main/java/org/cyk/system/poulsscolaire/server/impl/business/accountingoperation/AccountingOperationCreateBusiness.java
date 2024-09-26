package org.cyk.system.poulsscolaire.server.impl.business.accountingoperation;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService.AccountingOperationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService.AccountingOperationSaveRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperation;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlan;
import org.cyk.system.poulsscolaire.server.impl.persistence.School;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolPersistence;

/**
 * Cette classe représente la création de {@link AccountingOperation}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationCreateBusiness
    extends AbstractIdentifiableCreateBusiness<AccountingOperation, AccountingOperationPersistence,
        AccountingOperationValidator, AccountingOperationCreateRequestDto> {

  @Inject
  @Getter
  AccountingOperationPersistence persistence;

  @Inject
  @Getter
  AccountingOperationValidator validator;

  @Inject
  AccountingPlanValidator planValidator;

  @Inject
  SchoolPersistence schoolPersistence;

  @Override
  protected Object[] validate(AccountingOperationCreateRequestDto request, StringList messages) {
    AccountingPlan plan =
        planValidator.validateInstanceByIdentifier(request.getPlanIdentifier(), messages);
    validator.validateAccountType(request.getAccountType(), messages);
    validator.validateBeneficiary(request.getBeneficiary(), request.getAccountType(), messages);
    return new Object[] {plan};
  }

  @Override
  protected void setFields(AccountingOperation accountingOperation, Object[] array,
      AccountingOperationCreateRequestDto request) {
    super.setFields(accountingOperation, array, request);
    long count = persistence.countAll();
    accountingOperation.plan = (AccountingPlan) array[0];
    accountingOperation.accountType = request.getAccountType();
    accountingOperation.setCode(request.getAccountType().getCode() + count);

    computeName(accountingOperation, count);
    accountingOperation.schoolIdentifier = request.getSchoolIdentifier();
    computeBeneficiary(accountingOperation, request);
  }

  void computeName(AccountingOperation accountingOperation, long count) {
    Core.runIfStringBlank(accountingOperation.name,
        () -> accountingOperation.name = accountingOperation.accountType + " " + count);
  }

  void computeBeneficiary(AccountingOperation accountingOperation,
      AccountingOperationSaveRequestDto request) {
    accountingOperation.beneficiary = request.getBeneficiary();
    Core.runIfStringBlank(accountingOperation.beneficiary, () -> {
      School school = schoolPersistence.getByIdentifier(accountingOperation.schoolIdentifier);
      accountingOperation.beneficiary = school.name;
    });


  }
}
