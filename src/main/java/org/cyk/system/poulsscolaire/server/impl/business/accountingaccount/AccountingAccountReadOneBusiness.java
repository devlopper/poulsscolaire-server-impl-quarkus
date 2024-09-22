package org.cyk.system.poulsscolaire.server.impl.business.accountingaccount;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountPersistence;

/**
 * Cette classe représente l'obtention de {@link AccountingAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountReadOneBusiness
    extends AbstractIdentifiableReadOneBusiness<AccountingAccount, AccountingAccountPersistence,
        AccountingAccountDynamicQuery, AccountingAccountDto, AccountingAccountMapper> {

  protected AccountingAccountReadOneBusiness() {
    super(AccountingAccountDto.class);
  }

  @Inject
  @Getter
  AccountingAccountPersistence persistence;

  @Inject
  @Getter
  AccountingAccountDynamicQuery dynamicQuery;

  @Inject
  @Getter
  AccountingAccountMapper mapper;
}
