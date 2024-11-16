package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodableNamablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Budget}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetPersistence
    extends AbstractIdentifiableCodableNamablePersistence<Budget> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public BudgetPersistence() {
    super(Budget.class);
    name = BudgetDto.NAME;
    pluralName = BudgetDto.PLURAL_NAME;
  }
}
