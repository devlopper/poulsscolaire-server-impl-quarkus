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
  protected void validate(FundingReturnRequestDto request, StringList messages, Funding funding) {
    super.validate(request, messages, funding);
    validate(funding, request.getReason(), messages);
  }

  void validate(Funding funding, String reason, StringList messages) {
    validationHelper.validateBlankByName(this, reason, "motif", messages);
    validator.validateStatusChange(funding.status, FundingStatus.RETURNED, messages);
  }

  @Override
  protected void prepare(Funding funding, FundingReturnRequestDto request) {
    super.prepare(funding, request);
    prepare(funding, request.getReason());
  }

  void prepare(Funding funding, String reason) {
    funding.status = FundingStatus.RETURNED;
    funding.statusReason = reason;
  }
  
  @Override
  protected void processResponse(Funding funding, IdentifiableResponseDto response) {
    super.processResponse(funding, response);
    ((FundingStatusUpdateResponseDto) response).initialize(funding.status, funding.status.getName(),
        funding.statusReason);
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

