package org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionRegistrationService.StockDistributionRegistrationUpdateQuantityRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistration;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistrationPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovementPersistence;

/**
 * Cette classe représente la mise à jour de quantité de {@link StockDistributionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionRegistrationUpdateQuantityBusiness extends
    AbstractIdentifiableUpdateBusiness<StockDistributionRegistration,
        StockDistributionRegistrationPersistence, StockDistributionRegistrationValidator,
        StockDistributionRegistrationUpdateQuantityRequestDto> {

  @Inject
  @Getter
  StockDistributionRegistrationPersistence persistence;

  @Inject
  @Getter
  StockDistributionRegistrationValidator validator;

  @Inject
  StockMovementPersistence stockMovementPersistence;

  @Override
  protected void prepare(StockDistributionRegistration stockDistributionRegistration,
      StockDistributionRegistrationUpdateQuantityRequestDto request) {
    super.prepare(stockDistributionRegistration, request);
    
    stockDistributionRegistration.movement.audit = stockDistributionRegistration.audit;
    stockDistributionRegistration.movement.quantity = request.getQuantity();
  }

  @Override
  protected void doTransact(StockDistributionRegistration stockDistributionRegistration) {
    stockMovementPersistence.update(stockDistributionRegistration.movement);
    super.doTransact(stockDistributionRegistration); 
  }
}
