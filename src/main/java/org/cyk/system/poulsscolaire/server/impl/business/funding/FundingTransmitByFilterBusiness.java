package org.cyk.system.poulsscolaire.server.impl.business.funding;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateByFilterBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.ByFilterRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingStatus;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;

/**
 * Cette classe représente la transmission par filtre de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingTransmitByFilterBusiness extends AbstractIdentifiableUpdateByFilterBusiness<
    Funding, FundingPersistence, FundingDynamicQuery, FundingValidator, ByFilterRequestDto> {

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
  FundingTransmitBusiness transmitBusiness;

  @Override
  protected void validateIdentifiable(Funding funding, ByFilterRequestDto request,
      StringList messages) {
    super.validateIdentifiable(funding, request, messages);
    transmitBusiness.validate(funding, messages);
  }

  @Override
  protected void prepareIdentifiable(Funding funding, ByFilterRequestDto request) {
    super.prepareIdentifiable(funding, request);
    transmitBusiness.prepare(funding);
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

