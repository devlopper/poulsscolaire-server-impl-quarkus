package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodableNamablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link AccountingOperationAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationAccountPersistence
    extends AbstractIdentifiableCodableNamablePersistence<AccountingOperationAccount> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public AccountingOperationAccountPersistence() {
    super(AccountingOperationAccount.class);
    name = AccountingOperationAccountDto.NAME;
    pluralName = AccountingOperationAccountDto.PLURAL_NAME;
  }
}
