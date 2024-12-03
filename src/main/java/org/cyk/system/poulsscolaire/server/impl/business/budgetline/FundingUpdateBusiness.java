package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;

/**
 * Cette classe représente la mise à jour de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingUpdateBusiness extends AbstractIdentifiableUpdateBusiness<Funding,
    FundingPersistence, FundingValidator, FundingUpdateRequestDto> {

  @Inject
  @Getter
  FundingPersistence persistence;

  @Inject
  @Getter
  FundingValidator validator;

  
}
