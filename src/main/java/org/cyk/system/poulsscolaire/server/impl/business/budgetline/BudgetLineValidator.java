package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLine;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLinePersistence;

/**
 * Cette class représente un validateur de {@link BudgetLine}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetLineValidator
    extends AbstractIdentifiableValidator<BudgetLine> {

  @Inject
  @Getter
  private BudgetLinePersistence persistence;

}
