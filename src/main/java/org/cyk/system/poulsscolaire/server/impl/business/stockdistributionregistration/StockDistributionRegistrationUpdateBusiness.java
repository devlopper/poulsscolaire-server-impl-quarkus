package org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionRegistrationService.StockDistributionRegistrationUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistration;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistrationPersistence;

/**
 * Cette classe représente la mise à jour de {@link StockDistributionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionRegistrationUpdateBusiness extends
    AbstractIdentifiableUpdateBusiness<StockDistributionRegistration,
        StockDistributionRegistrationPersistence, StockDistributionRegistrationValidator,
        StockDistributionRegistrationUpdateRequestDto> {

  @Inject
  @Getter
  StockDistributionRegistrationPersistence persistence;

  @Inject
  @Getter
  StockDistributionRegistrationValidator validator;

  @Inject
  StockValidator stockValidator;


}
