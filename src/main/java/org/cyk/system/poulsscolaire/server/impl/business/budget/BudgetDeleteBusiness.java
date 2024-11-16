package org.cyk.system.poulsscolaire.server.impl.business.budget;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetPersistence;

/**
 * Cette classe représente la suppression de {@link Budget}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetDeleteBusiness
    extends AbstractIdentifiableDeleteBusiness<Budget, BudgetPersistence,
        BudgetValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  BudgetPersistence persistence;

  @Inject
  @Getter
  BudgetValidator validator;
}
