package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccountPersistence;

/**
 * Cette classe représente la suppression de {@link AccountingOperationAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationAccountDeleteBusiness extends
    AbstractIdentifiableDeleteBusiness<AccountingOperationAccount,
        AccountingOperationAccountPersistence, AccountingOperationAccountValidator,
        DeleteOneRequestDto> {

  @Inject
  @Getter
  AccountingOperationAccountPersistence persistence;

  @Inject
  @Getter
  AccountingOperationAccountValidator validator;
}
