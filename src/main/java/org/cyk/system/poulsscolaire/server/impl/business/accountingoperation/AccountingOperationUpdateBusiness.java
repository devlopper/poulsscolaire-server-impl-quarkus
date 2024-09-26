package org.cyk.system.poulsscolaire.server.impl.business.accountingoperation;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService.AccountingOperationUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperation;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationPersistence;

/**
 * Cette classe représente la mise à jour de {@link AccountingOperation}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationUpdateBusiness
    extends AbstractIdentifiableUpdateBusiness<AccountingOperation, AccountingOperationPersistence,
        AccountingOperationValidator, AccountingOperationUpdateRequestDto> {

  @Inject
  @Getter
  AccountingOperationPersistence persistence;

  @Inject
  @Getter
  AccountingOperationValidator validator;

  @Inject
  AccountingPlanValidator planValidator;

  @Inject
  AccountingOperationCreateBusiness createBusiness;

  @Override
  protected void validate(AccountingOperationUpdateRequestDto request, StringList messages,
      AccountingOperation accountingOperation) {
    super.validate(request, messages, accountingOperation);
    accountingOperation.plan =
        planValidator.validateInstanceByIdentifier(request.getPlanIdentifier(), messages);
    validator.validateAccountType(request.getAccountType(), messages);
    validator.validateBeneficiary(request.getBeneficiary(), request.getAccountType(), messages);
  }

  @Override
  protected void prepare(AccountingOperation accountingOperation,
      AccountingOperationUpdateRequestDto request) {
    super.prepare(accountingOperation, request);
    accountingOperation.schoolIdentifier = request.getSchoolIdentifier();
    accountingOperation.beneficiary = request.getBeneficiary();
    accountingOperation.accountType = request.getAccountType();

    createBusiness.computeName(accountingOperation, persistence.countAll());
    createBusiness.computeBeneficiary(accountingOperation, request);
  }
}
