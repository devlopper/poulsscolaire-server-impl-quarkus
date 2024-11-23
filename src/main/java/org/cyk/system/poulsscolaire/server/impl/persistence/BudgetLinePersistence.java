package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link BudgetLine}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetLinePersistence extends AbstractIdentifiablePersistence<BudgetLine> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public BudgetLinePersistence() {
    super(BudgetLine.class);
    name = BudgetLineDto.NAME;
    pluralName = BudgetLineDto.PLURAL_NAME;
  }
}
