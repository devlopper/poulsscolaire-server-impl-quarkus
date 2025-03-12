package org.cyk.system.poulsscolaire.server.impl.business.funding;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingReturnRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingStatusUpdateResponseDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingStatus;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;

/**
 * Cette classe représente le retour de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingReturnBusiness extends AbstractIdentifiableUpdateBusiness<Funding,
    FundingPersistence, FundingValidator, FundingReturnRequestDto> {

  @Inject
  @Getter
  FundingPersistence persistence;

  @Inject
  @Getter
  FundingValidator validator;

  @Override
  protected Class<? extends IdentifiableResponseDto> getResponseClass() {
    return FundingStatusUpdateResponseDto.class;
  }

  @Override
  protected void validate(FundingReturnRequestDto request, StringList messages,
      Funding processing) {
    super.validate(request, messages, processing);
    validationHelper.validateBlankByName(this, request.getReason(), "motif", messages);
    validator.validateStatusChange(processing.status, FundingStatus.RETURNED, messages);
  }

  @Override
  protected void prepare(Funding processing, FundingReturnRequestDto request) {
    super.prepare(processing, request);
    processing.status = FundingStatus.RETURNED;
    processing.statusReason = request.getReason();
  }

  @Override
  protected void processResponse(Funding budget, IdentifiableResponseDto response) {
    super.processResponse(budget, response);
    ((FundingStatusUpdateResponseDto) response).initialize(budget.status, budget.status.getName(),
        budget.statusReason);
  }

  @Override
  protected String getActionName() {
    return FundingStatus.RETURNED.getActionName();
  }

  @Override
  protected String getActionDone() {
    return FundingStatus.RETURNED.getName();
  }
}

