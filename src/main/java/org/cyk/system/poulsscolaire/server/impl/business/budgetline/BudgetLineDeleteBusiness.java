package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLine;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLinePersistence;

/**
 * Cette classe représente la suppression de {@link BudgetLine}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetLineDeleteBusiness
    extends AbstractIdentifiableDeleteBusiness<BudgetLine, BudgetLinePersistence,
        BudgetLineValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  BudgetLinePersistence persistence;

  @Inject
  @Getter
  BudgetLineValidator validator;
}
