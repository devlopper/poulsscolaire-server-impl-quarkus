package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link AccountingOperation}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationPersistence
    extends AbstractIdentifiableCodablePersistence<AccountingOperation> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public AccountingOperationPersistence() {
    super(AccountingOperation.class);
    name = AccountingOperationDto.NAME;
    pluralName = AccountingOperationDto.PLURAL_NAME;
  }
}
