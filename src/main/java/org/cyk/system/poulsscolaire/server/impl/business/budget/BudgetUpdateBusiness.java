package org.cyk.system.poulsscolaire.server.impl.business.budget;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService.BudgetUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetPersistence;

/**
 * Cette classe représente la mise à jour de {@link Budget}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetUpdateBusiness extends AbstractIdentifiableUpdateBusiness<Budget,
    BudgetPersistence, BudgetValidator, BudgetUpdateRequestDto> {

  @Inject
  @Getter
  BudgetPersistence persistence;

  @Inject
  @Getter
  BudgetValidator validator;

  @Inject
  AccountingPlanValidator planValidator;

  @Inject
  BudgetCreateBusiness createBusiness;

  @Override
  protected void validate(BudgetUpdateRequestDto request, StringList messages,
      Budget accountingOperation) {
    super.validate(request, messages, accountingOperation);
    accountingOperation.accountingPlan =
        planValidator.validateInstanceByIdentifier(request.getAccountingPlanIdentifier(), messages);
  }

  @Override
  protected void prepare(Budget accountingOperation, BudgetUpdateRequestDto request) {
    super.prepare(accountingOperation, request);
    accountingOperation.schoolIdentifier = request.getSchoolIdentifier();
  }
}
