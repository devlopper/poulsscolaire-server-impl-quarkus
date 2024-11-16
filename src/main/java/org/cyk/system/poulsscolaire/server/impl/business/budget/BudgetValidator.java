package org.cyk.system.poulsscolaire.server.impl.business.budget;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetPersistence;

/**
 * Cette class représente un validateur de {@link Budget}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetValidator
    extends AbstractIdentifiableCodableValidator<Budget> {

  @Inject
  @Getter
  private BudgetPersistence persistence;

}
