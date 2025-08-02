package org.cyk.system.poulsscolaire.server.impl.business.budget;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService.BudgetCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetStatus;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlan;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolPersistence;

/**
 * Cette classe représente la création de {@link Budget}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetCreateBusiness extends AbstractIdentifiableCreateBusiness<Budget,
    BudgetPersistence, BudgetValidator, BudgetCreateRequestDto> {

  @Inject
  @Getter
  BudgetPersistence persistence;

  @Inject
  @Getter
  BudgetValidator validator;

  @Inject
  AccountingPlanValidator planValidator;

  @Inject
  SchoolPersistence schoolPersistence;

  @Override
  protected Object[] validate(BudgetCreateRequestDto request, StringList messages) {
    AccountingPlan plan =
        planValidator.validateInstanceByIdentifier(request.getAccountingPlanIdentifier(), messages);
    return new Object[] {plan};
  }

  @Override
  protected void setFields(Budget budget, Object[] array,
      BudgetCreateRequestDto request) {
    super.setFields(budget, array, request);
    budget.code = "B" + request.getSchoolIdentifier() + request.getYear();
    budget.name = "Budget " + request.getYear();
    budget.schoolIdentifier = request.getSchoolIdentifier();
    budget.accountingPlan = (AccountingPlan) array[0];
    budget.year = request.getYear();
    budget.deadline = request.getDeadline();
    budget.status = BudgetStatus.CREATED;
  }
}
