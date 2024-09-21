package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodableNamablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link AccountingPlan}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingPlanPersistence
    extends AbstractIdentifiableCodableNamablePersistence<AccountingPlan> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public AccountingPlanPersistence() {
    super(AccountingPlan.class);
    name = AccountingPlanDto.NAME;
    pluralName = AccountingPlanDto.PLURAL_NAME;
  }
}
