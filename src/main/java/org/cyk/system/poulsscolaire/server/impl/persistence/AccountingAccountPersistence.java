package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodableNamablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link AccountingAccount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountPersistence
    extends AbstractIdentifiableCodableNamablePersistence<AccountingAccount> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public AccountingAccountPersistence() {
    super(AccountingAccount.class);
    name = AccountingAccountDto.NAME;
    pluralName = AccountingAccountDto.PLURAL_NAME;
  }
}
