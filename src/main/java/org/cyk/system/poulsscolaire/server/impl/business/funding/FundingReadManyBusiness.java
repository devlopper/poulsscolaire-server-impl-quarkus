package org.cyk.system.poulsscolaire.server.impl.business.funding;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;

/**
 * Cette classe représente l'obtention de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<Funding, FundingPersistence,
        FundingDynamicQuery, FundingDto, FundingMapper,
        FundingGetManyResponseDto> {

  protected FundingReadManyBusiness() {
    super(FundingGetManyResponseDto.class);
  }

  @Inject
  @Getter
  FundingPersistence persistence;

  @Inject
  @Getter
  FundingDynamicQuery dynamicQuery;

  @Inject
  @Getter
  FundingMapper mapper;
}
