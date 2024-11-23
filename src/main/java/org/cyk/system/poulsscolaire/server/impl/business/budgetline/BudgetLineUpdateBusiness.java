package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineService.BudgetLineUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLine;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLinePersistence;

/**
 * Cette classe représente la mise à jour de {@link BudgetLine}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetLineUpdateBusiness extends AbstractIdentifiableUpdateBusiness<BudgetLine,
    BudgetLinePersistence, BudgetLineValidator, BudgetLineUpdateRequestDto> {

  @Inject
  @Getter
  BudgetLinePersistence persistence;

  @Inject
  @Getter
  BudgetLineValidator validator;

  
}
