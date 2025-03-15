package org.cyk.system.poulsscolaire.server.impl.business.funding;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateByFilterBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.ByFilterWithReasonRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingStatus;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;

/**
 * Cette classe représente le retour par filtre de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingReturnByFilterBusiness
    extends AbstractIdentifiableUpdateByFilterBusiness<Funding, FundingPersistence,
        FundingDynamicQuery, FundingValidator, ByFilterWithReasonRequestDto> {

  @Inject
  @Getter
  FundingPersistence persistence;

  @Inject
  @Getter
  FundingDynamicQuery dynamicQuery;

  @Inject
  @Getter
  FundingValidator validator;

  @Inject
  FundingReturnBusiness returnBusiness;

  @Override
  protected void validateIdentifiable(Funding funding, ByFilterWithReasonRequestDto request,
      StringList messages) {
    super.validateIdentifiable(funding, request, messages);
    returnBusiness.validate(funding, request.getReason(), messages);
  }

  @Override
  protected void prepareIdentifiable(Funding funding, ByFilterWithReasonRequestDto request) {
    super.prepareIdentifiable(funding, request);
    returnBusiness.prepare(funding, request.getReason());
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

