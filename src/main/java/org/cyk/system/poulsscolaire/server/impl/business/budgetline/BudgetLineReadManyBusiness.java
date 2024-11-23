package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineService.BudgetLineGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLine;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLineDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLinePersistence;

/**
 * Cette classe représente l'obtention de {@link BudgetLine}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetLineReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<BudgetLine, BudgetLinePersistence,
        BudgetLineDynamicQuery, BudgetLineDto, BudgetLineMapper,
        BudgetLineGetManyResponseDto> {

  protected BudgetLineReadManyBusiness() {
    super(BudgetLineGetManyResponseDto.class);
  }

  @Inject
  @Getter
  BudgetLinePersistence persistence;

  @Inject
  @Getter
  BudgetLineDynamicQuery dynamicQuery;

  @Inject
  @Getter
  BudgetLineMapper mapper;
}
