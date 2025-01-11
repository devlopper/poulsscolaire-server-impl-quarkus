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
 * Cette classe représente l'acceptation de {@link Budget}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetAcceptBusiness extends AbstractIdentifiableUpdateBusiness<Budget,
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
  protected void validate(ByIdentifierRequestDto request, StringList messages, Budget processing) {
    super.validate(request, messages, processing);
    validator.validateStatusChange(processing.status, BudgetStatus.ACCEPTED, messages);
  }

  @Override
  protected void prepare(Budget processing, ByIdentifierRequestDto request) {
    super.prepare(processing, request);
    processing.status = BudgetStatus.ACCEPTED;
  }

  @Override
  protected void processResponse(Budget budget, IdentifiableResponseDto response) {
    super.processResponse(budget, response);
    ((BudgetStatusUpdateResponseDto) response).initialize(budget.status,
        budget.status.getName(), budget.statusReason);
  }

  @Override
  protected String getActionName() {
    return BudgetStatus.ACCEPTED.getActionName();
  }

  @Override
  protected String getActionDone() {
    return BudgetStatus.ACCEPTED.getName();
  }
}

