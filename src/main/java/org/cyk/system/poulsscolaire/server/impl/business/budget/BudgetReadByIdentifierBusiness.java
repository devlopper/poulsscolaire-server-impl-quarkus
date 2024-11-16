package org.cyk.system.poulsscolaire.server.impl.business.budget;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link Budget}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<Budget,
        BudgetPersistence, BudgetDynamicQuery, BudgetDto,
        BudgetMapper> {

  protected BudgetReadByIdentifierBusiness() {
    super(BudgetDto.class);
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
