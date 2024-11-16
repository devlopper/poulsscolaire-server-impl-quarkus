package org.cyk.system.poulsscolaire.server.impl.business.budget;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService.BudgetGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetPersistence;

/**
 * Cette classe représente l'obtention de {@link Budget}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<Budget, BudgetPersistence,
        BudgetDynamicQuery, BudgetDto, BudgetMapper,
        BudgetGetManyResponseDto> {

  protected BudgetReadManyBusiness() {
    super(BudgetGetManyResponseDto.class);
  }

  @Inject
  @Getter
  BudgetPersistence persistence;

  @Inject
  @Getter
  BudgetDynamicQuery dynamicQuery;

  @Inject
  @Getter
  BudgetMapper mapper;
}
