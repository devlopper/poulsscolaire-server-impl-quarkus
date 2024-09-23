package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccountPersistence;

/**
 * Cette class représente un validateur de {@link AccountingOperationAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationAccountValidator
    extends AbstractIdentifiableCodableNamableValidator<AccountingOperationAccount> {

  @Inject
  @Getter
  private AccountingOperationAccountPersistence persistence;

  
}
