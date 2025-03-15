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
 * Cette classe représente l'approbation de {@link Funding}.
 *
 * @author Christian
 */
@ApplicationScoped
public class FundingApproveBusiness extends AbstractIdentifiableUpdateBusiness<Funding,
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
  protected void validate(ByIdentifierRequestDto request, StringList messages,
      Funding funding) {
    super.validate(request, messages, funding);
    validate(funding, messages);
  }
  
  void validate(Funding funding, StringList messages) {
    validator.validateStatusChange(funding.status, FundingStatus.APPROVED, messages);
  }

  @Override
  protected void prepare(Funding funding, ByIdentifierRequestDto request) {
    super.prepare(funding, request);
    prepare(funding);
  }

  void prepare(Funding funding) {
    funding.status = FundingStatus.APPROVED;
    funding.statusReason = null;
  }
  
  @Override
  protected void processResponse(Funding budget, IdentifiableResponseDto response) {
    super.processResponse(budget, response);
    ((FundingStatusUpdateResponseDto) response).initialize(budget.status,
        budget.status.getName(), budget.statusReason);
  }

  @Override
  protected String getActionName() {
    return FundingStatus.APPROVED.getActionName();
  }

  @Override
  protected String getActionDone() {
    return FundingStatus.APPROVED.getName();
  }
}

