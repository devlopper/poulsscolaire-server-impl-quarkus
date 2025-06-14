package org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.StockDistributionRegistrationDto;
import org.cyk.system.poulsscolaire.server.api.registration.StockDistributionRegistrationService.StockDistributionRegistrationGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistration;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistrationDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistrationPersistence;

/**
 * Cette classe représente l'obtention de {@link StockDistributionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionRegistrationReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<StockDistributionRegistration,
        StockDistributionRegistrationPersistence, StockDistributionRegistrationDynamicQuery,
        StockDistributionRegistrationDto, StockDistributionRegistrationMapper,
        StockDistributionRegistrationGetManyResponseDto> {

  protected StockDistributionRegistrationReadManyBusiness() {
    super(StockDistributionRegistrationGetManyResponseDto.class);
  }

  @Inject
  @Getter
  StockDistributionRegistrationPersistence persistence;

  @Inject
  @Getter
  StockDistributionRegistrationDynamicQuery dynamicQuery;

  @Inject
  @Getter
  StockDistributionRegistrationMapper mapper;
}
