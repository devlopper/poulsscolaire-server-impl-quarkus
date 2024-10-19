package org.cyk.system.poulsscolaire.server.impl.business.accountingoperation;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountType;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService.AccountingOperationCreateRequestDto;
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
    setFields(accountingOperation, (AccountingPlan) array[0], request.getAccountType(),
        request.getSchoolIdentifier(), request.getBeneficiary());
  }

  /**
   * Cette méthode permet d'aasigner les champs.
   *
   * @param accountingOperation {@link AccountingOperation}
   * @param plan {@link AccountingPlan}
   * @param accountingAccountType {@link Accountingtype}
   * @param schoolIdentifier identifiant {@link School}
   * @param beneficiary bénéficiare
   */
  public void setFields(AccountingOperation accountingOperation, AccountingPlan plan,
      AccountingAccountType accountingAccountType, String schoolIdentifier, String beneficiary) {
    long count = persistence.countAll();
    accountingOperation.plan = plan;
    accountingOperation.accountType = accountingAccountType;
    accountingOperation.setCode(accountingAccountType.getCode() + count);

    computeName(accountingOperation, count);
    accountingOperation.schoolIdentifier = schoolIdentifier;
    computeBeneficiary(accountingOperation, beneficiary);

    accountingOperation.canceled = false;
  }

  void computeName(AccountingOperation accountingOperation, long count) {
    Core.runIfStringBlank(accountingOperation.name,
        () -> accountingOperation.name = accountingOperation.accountType + " " + count);
  }

  void computeBeneficiary(AccountingOperation accountingOperation, String beneficiary) {
    accountingOperation.beneficiary = beneficiary;
    Core.runIfStringBlank(accountingOperation.beneficiary, () -> {
      School school = schoolPersistence.getByIdentifier(accountingOperation.schoolIdentifier);
      accountingOperation.beneficiary = school.name;
    });
  }
  
  public void create(AccountingOperation accountingOperation) {
    persistence.create(accountingOperation);
  }
}
