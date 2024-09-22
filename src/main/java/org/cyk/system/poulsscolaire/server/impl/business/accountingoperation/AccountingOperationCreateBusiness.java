package org.cyk.system.poulsscolaire.server.impl.business.accountingoperation;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService.AccountingOperationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperation;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationPersistence;

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

  @Override
  protected void setFields(AccountingOperation accountingOperation, Object[] array,
      AccountingOperationCreateRequestDto request) {
    super.setFields(accountingOperation, array, request);
    accountingOperation.setCode(request.getAccountType().getCode() + persistence.countAll());
    accountingOperation.schoolIdentifier = request.getSchoolIdentifier();
    accountingOperation.beneficiary = request.getBeneficiary();
    accountingOperation.accountType = request.getAccountType();
  }
}
