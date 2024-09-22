package org.cyk.system.poulsscolaire.server.impl.business.accountingoperation;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService.AccountingOperationUpdateRequestDto;
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
  
  @Override
  protected void validate(AccountingOperationUpdateRequestDto request, StringList messages,
      AccountingOperation accountingOperation) {
    super.validate(request, messages, accountingOperation);
    accountingOperation.schoolIdentifier = request.getSchoolIdentifier();
    accountingOperation.beneficiary = request.getBeneficiary();
    accountingOperation.accountType = request.getAccountType();
  }
}
