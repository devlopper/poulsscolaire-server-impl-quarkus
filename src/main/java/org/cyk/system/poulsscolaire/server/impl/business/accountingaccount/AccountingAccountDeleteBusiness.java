package org.cyk.system.poulsscolaire.server.impl.business.accountingaccount;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountPersistence;

/**
 * Cette classe représente la suppression de {@link AccountingAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountDeleteBusiness
    extends AbstractIdentifiableDeleteBusiness<AccountingAccount, AccountingAccountPersistence,
        AccountingAccountValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  AccountingAccountPersistence persistence;

  @Inject
  @Getter
  AccountingAccountValidator validator;
}
