package org.cyk.system.poulsscolaire.server.impl.business.budget;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService.BudgetStatusUpdateResponseDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetStatus;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetPersistence;

/**
 * Cette classe représente l'approbation de {@link Budget}.
 *
 * @author Christian
 */
@ApplicationScoped
public class BudgetApproveBusiness extends AbstractIdentifiableUpdateBusiness<Budget,
    BudgetPersistence, BudgetValidator, ByIdentifierRequestDto> {

  @Inject
  @Getter
  BudgetPersistence persistence;

  @Inject
  @Getter
  BudgetValidator validator;

  @Override
  protected Class<? extends IdentifiableResponseDto> getResponseClass() {
    return BudgetStatusUpdateResponseDto.class;
  }

  @Override
  protected void validate(ByIdentifierRequestDto request, StringList messages,
      Budget budget) {
    super.validate(request, messages, budget);
    validator.validateStatusChange(budget.status, BudgetStatus.APPROVED, messages);
  }

  @Override
  protected void prepare(Budget budget, ByIdentifierRequestDto request) {
    super.prepare(budget, request);
    budget.status = BudgetStatus.APPROVED;
  }

  @Override
  protected void processResponse(Budget budget, IdentifiableResponseDto response) {
    super.processResponse(budget, response);
    ((BudgetStatusUpdateResponseDto) response).initialize(budget.status,
        budget.status.getName(), budget.statusReason);
  }

  @Override
  protected String getActionName() {
    return BudgetStatus.APPROVED.getActionName();
  }

  @Override
  protected String getActionDone() {
    return BudgetStatus.APPROVED.getName();
  }
}

