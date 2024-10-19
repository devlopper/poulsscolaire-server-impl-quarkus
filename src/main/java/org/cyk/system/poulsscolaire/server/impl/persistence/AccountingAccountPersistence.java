package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodableNamablePersistence;
import ci.gouv.dgbf.extension.server.persistence.query.SingleResultGetter;
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

  /**
   * Cette méthode permet d'obtenir {@link AccountingAccount} de paiement par identifiant
   * {@link School}.
   *
   * @param schoolIdentifier identifiant {@link School}
   * @return {@link AccountingAccount} de paiement
   */
  public AccountingAccount getPaymentBySchoolIdentifier(String schoolIdentifier) {
    return new SingleResultGetter<>(entityManager
        .createNamedQuery(AccountingAccount.QUERY_READ_PAYMENT_BY_SCHOOL_IDENTIFIER_IDENTIFIER,
            AccountingAccount.class)
        .setParameter("schoolIdentifier", schoolIdentifier)).get();
  }
}
