package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLine;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLineDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLinePersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link BudgetLine}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetLineReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<BudgetLine,
        BudgetLinePersistence, BudgetLineDynamicQuery, BudgetLineDto,
        BudgetLineMapper> {

  protected BudgetLineReadByIdentifierBusiness() {
    super(BudgetLineDto.class);
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
