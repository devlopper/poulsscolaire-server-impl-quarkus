package org.cyk.system.poulsscolaire.server.impl.business.stockmovement;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovement;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovementDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovementPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link StockMovement}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockMovementReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<StockMovement, StockMovementPersistence,
        StockMovementDynamicQuery, StockMovementDto, StockMovementMapper> {

  protected StockMovementReadByIdentifierBusiness() {
    super(StockMovementDto.class);
  }

  @Inject
  @Getter
  StockMovementPersistence persistence;

  @Inject
  @Getter
  StockMovementDynamicQuery dynamicQuery;

  @Inject
  @Getter
  StockMovementMapper mapper;
}
