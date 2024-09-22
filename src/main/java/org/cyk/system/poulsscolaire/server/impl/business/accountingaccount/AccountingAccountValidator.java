package org.cyk.system.poulsscolaire.server.impl.business.accountingaccount;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountType;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountPersistence;

/**
 * Cette class représente un validateur de {@link AccountingAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountValidator
    extends AbstractIdentifiableCodableNamableValidator<AccountingAccount> {

  @Inject
  @Getter
  private AccountingAccountPersistence persistence;

  public boolean validateType(AccountingAccountType type, StringList messages) {
    return validationHelper.validateNullByName(this, type, AccountingAccountType.LABEL, messages);
  }
}
