package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineService.BudgetLineCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountValidator;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetValidator;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLine;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLinePersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSource;

/**
 * Cette classe représente la création de {@link BudgetLine}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetLineCreateBusiness extends AbstractIdentifiableCreateBusiness<BudgetLine,
    BudgetLinePersistence, BudgetLineValidator, BudgetLineCreateRequestDto> {

  @Inject
  @Getter
  BudgetLinePersistence persistence;

  @Inject
  @Getter
  BudgetLineValidator validator;

  @Inject
  BudgetValidator budgetValidator;

  @Inject
  AccountingAccountValidator accountingAccountValidator;

  @Inject
  FundingSourceValidator fundingSourceValidator;

  @Override
  protected Object[] validate(BudgetLineCreateRequestDto request, StringList messages) {
    Budget budget =
        budgetValidator.validateInstanceByIdentifier(request.getBudgetIdentifier(), messages);
    AccountingAccount accountingAccount = accountingAccountValidator
        .validateInstanceByIdentifier(request.getAccountingAccountIdentifier(), messages);
    FundingSource fundingSource = fundingSourceValidator
        .validateInstanceByIdentifier(request.getFundingSourceIdentifier(), messages);
    return new Object[] {budget, accountingAccount, fundingSource};
  }

  @Override
  protected void setFields(BudgetLine budgetLine, Object[] array,
      BudgetLineCreateRequestDto request) {
    super.setFields(budgetLine, array, request);
    budgetLine.budget = (Budget) array[0];
    budgetLine.departmentIdentifier = request.getDepartmentIdentifier();
    budgetLine.accountingAccount = (AccountingAccount) array[1];
    budgetLine.fundingSource = (FundingSource) array[2];
    budgetLine.month = request.getMonth();
    budgetLine.amount = request.getAmount();
    budgetLine.justification = request.getJustification();
  }
}
