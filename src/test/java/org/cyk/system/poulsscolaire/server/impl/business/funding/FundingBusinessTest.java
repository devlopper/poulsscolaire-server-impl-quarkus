package org.cyk.system.poulsscolaire.server.impl.business.funding;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.test.AbstractTest;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetStatus;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingUpdateAmountRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.junit.jupiter.api.Test;

class FundingBusinessTest extends AbstractTest {

  FundingUpdateAmountBusiness fundingUpdateAmountBusiness = new FundingUpdateAmountBusiness();

  @Test
  void funding_updateAmount_validate_whenBudgetStatusApproved() {
    FundingUpdateAmountRequestDto request = new FundingUpdateAmountRequestDto();
    Funding funding = new Funding();
    funding.budget = new Budget();
    funding.budget.status = BudgetStatus.APPROVED;
    StringList messages = new StringList();
    fundingUpdateAmountBusiness.validate(request, messages, funding);
    assertNotNull(messages.getList());
  }
}
