package org.cyk.system.poulsscolaire.server.impl.business.funding;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingStatusUpdateResponseDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingStatus;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;

/**
 * Cette classe représente la transmission de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingTransmitBusiness extends AbstractIdentifiableUpdateBusiness<Funding,
    FundingPersistence, FundingValidator, ByIdentifierRequestDto> {

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
  protected void validate(ByIdentifierRequestDto request, StringList messages, Funding processing) {
    super.validate(request, messages, processing);
    validator.validateStatusChange(processing.status, FundingStatus.TRANSMITTED, messages);
  }

  @Override
  protected void prepare(Funding processing, ByIdentifierRequestDto request) {
    super.prepare(processing, request);
    processing.status = FundingStatus.TRANSMITTED;
  }

  @Override
  protected void processResponse(Funding budget, IdentifiableResponseDto response) {
    super.processResponse(budget, response);
    ((FundingStatusUpdateResponseDto) response).initialize(budget.status,
        budget.status.getName(), budget.statusReason);
  }

  @Override
  protected String getActionName() {
    return FundingStatus.TRANSMITTED.getActionName();
  }

  @Override
  protected String getActionDone() {
    return FundingStatus.TRANSMITTED.getName();
  }
}

