package org.cyk.system.poulsscolaire.server.impl.business.fundingsource;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceService.FundingSourceUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSource;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSourcePersistence;

/**
 * Cette classe représente la mise à jour de {@link FundingSource}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingSourceUpdateBusiness extends AbstractIdentifiableUpdateBusiness<FundingSource,
    FundingSourcePersistence, FundingSourceValidator, FundingSourceUpdateRequestDto> {

  @Inject
  @Getter
  FundingSourcePersistence persistence;

  @Inject
  @Getter
  FundingSourceValidator validator;
}
