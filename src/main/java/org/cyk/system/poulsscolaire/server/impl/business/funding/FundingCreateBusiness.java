package org.cyk.system.poulsscolaire.server.impl.business.funding;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingStatus;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountValidator;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetValidator;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSource;

/**
 * Cette classe représente la création de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingCreateBusiness extends AbstractIdentifiableCreateBusiness<Funding,
    FundingPersistence, FundingValidator, FundingCreateRequestDto> {

  @Inject
  @Getter
  FundingPersistence persistence;

  @Inject
  @Getter
  FundingValidator validator;

  @Inject
  BudgetValidator budgetValidator;

  @Inject
  AccountingAccountValidator accountingAccountValidator;

  @Inject
  FundingSourceValidator fundingSourceValidator;

  @Override
  protected Object[] validate(FundingCreateRequestDto request, StringList messages) {
    Budget budget =
        budgetValidator.validateInstanceByIdentifier(request.getBudgetIdentifier(), messages);
    AccountingAccount accountingAccount = accountingAccountValidator
        .validateInstanceByIdentifier(request.getAccountingAccountIdentifier(), messages);
    FundingSource fundingSource = fundingSourceValidator
        .validateInstanceByIdentifier(request.getSourceIdentifier(), messages);
    return new Object[] {budget, accountingAccount, fundingSource};
  }

  @Override
  protected void setFields(Funding funding, Object[] array,
      FundingCreateRequestDto request) {
    super.setFields(funding, array, request);
    funding.budget = (Budget) array[0];
    funding.departmentIdentifier = request.getDepartmentIdentifier();
    funding.accountingAccount = (AccountingAccount) array[1];
    funding.source = (FundingSource) array[2];
    funding.month = request.getMonth();
    funding.amount = request.getAmount();
    funding.justification = request.getJustification();
    funding.status = FundingStatus.CREATED;
  }
}
