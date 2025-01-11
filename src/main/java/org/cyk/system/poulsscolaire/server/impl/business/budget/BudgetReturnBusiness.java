package org.cyk.system.poulsscolaire.server.impl.business.budget;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService.BudgetReturnRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService.BudgetStatusUpdateResponseDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetStatus;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetPersistence;

/**
 * Cette classe représente le retour de {@link Budget}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetReturnBusiness extends AbstractIdentifiableUpdateBusiness<Budget,
    BudgetPersistence, BudgetValidator, BudgetReturnRequestDto> {

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
  protected void validate(BudgetReturnRequestDto request, StringList messages, Budget processing) {
    super.validate(request, messages, processing);
    validationHelper.validateBlankByName(this, request.getReason(), "motif", messages);
    validator.validateStatusChange(processing.status, BudgetStatus.RETURNED, messages);
  }

  @Override
  protected void prepare(Budget processing, BudgetReturnRequestDto request) {
    super.prepare(processing, request);
    processing.status = BudgetStatus.RETURNED;
    processing.statusReason = request.getReason();
  }

  @Override
  protected void processResponse(Budget budget, IdentifiableResponseDto response) {
    super.processResponse(budget, response);
    ((BudgetStatusUpdateResponseDto) response).initialize(budget.status,
        budget.status.getName(), budget.statusReason);
  }

  @Override
  protected String getActionName() {
    return BudgetStatus.RETURNED.getActionName();
  }

  @Override
  protected String getActionDone() {
    return BudgetStatus.RETURNED.getName();
  }
}

