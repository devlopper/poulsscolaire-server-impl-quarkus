package org.cyk.system.poulsscolaire.server.impl.business.accountingaccount;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link AccountingAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<AccountingAccount, AccountingAccountPersistence,
        AccountingAccountDynamicQuery, AccountingAccountDto, AccountingAccountMapper> {

  protected AccountingAccountReadByIdentifierBusiness() {
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
