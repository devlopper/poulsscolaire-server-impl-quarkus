package org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.StockDistributionRegistrationService.StockDistributionRegistrationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationValidator;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistribution;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistration;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistrationPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovement;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovementPersistence;

/**
 * Cette classe représente la création d'un {@link StockDistributionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionRegistrationCreateBusiness extends
    AbstractIdentifiableCreateBusiness<StockDistributionRegistration,
        StockDistributionRegistrationPersistence, StockDistributionRegistrationValidator,
        StockDistributionRegistrationCreateRequestDto> {

  @Inject
  @Getter
  StockDistributionRegistrationPersistence persistence;

  @Inject
  @Getter
  StockDistributionRegistrationValidator validator;

  @Inject
  StockDistributionValidator distributionValidator;

  @Inject
  RegistrationValidator registrationValidator;

  @Inject
  StockMovementPersistence movementPersistence;
  
  @Override
  protected Object[] validate(StockDistributionRegistrationCreateRequestDto request,
      StringList messages) {
    StockDistribution distribution = distributionValidator
        .validateInstanceByIdentifier(request.getStockDistributionIdentifier(), messages);
    Registration registration = registrationValidator
        .validateInstanceByIdentifier(request.getRegistrationIdentifier(), messages);
    return new Object[] {distribution, registration};
  }

  @Override
  protected void setFields(StockDistributionRegistration stockDistributionRegistration,
      Object[] array, StockDistributionRegistrationCreateRequestDto request) {
    super.setFields(stockDistributionRegistration, array, request);
    stockDistributionRegistration.distribution = (StockDistribution) array[0];
    stockDistributionRegistration.registration = (Registration) array[1];

    StockMovement movement = new StockMovement();
    movement.generateIdentifier();
    movement.audit = stockDistributionRegistration.audit;
    movement.stock = stockDistributionRegistration.distribution.stock;
    movement.quantity = request.getQuantity();

    stockDistributionRegistration.movement = movement;
  }

  @Override
  protected void doTransact(StockDistributionRegistration stockDistributionRegistration) {
    movementPersistence.create(stockDistributionRegistration.movement);
    super.doTransact(stockDistributionRegistration);
    
  }
}
