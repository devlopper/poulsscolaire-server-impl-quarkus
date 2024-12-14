package org.cyk.system.poulsscolaire.server.impl.business.stockmovement;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementService.StockMovementUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovement;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovementPersistence;

/**
 * Cette classe représente la mise à jour de {@link StockMovement}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockMovementUpdateBusiness extends AbstractIdentifiableUpdateBusiness<StockMovement,
    StockMovementPersistence, StockMovementValidator, StockMovementUpdateRequestDto> {

  @Inject
  @Getter
  StockMovementPersistence persistence;

  @Inject
  @Getter
  StockMovementValidator validator;

  @Inject
  StockValidator stockValidator;

  @Override
  protected void validate(StockMovementUpdateRequestDto request, StringList messages,
      StockMovement stockMovement) {
    super.validate(request, messages, stockMovement);
    stockMovement.stock =
        stockValidator.validateInstanceByIdentifier(request.getStockIdentifier(), messages);
    validator.validateQuantity(request.getQuantity(), messages);
  }

  @Override
  protected void prepare(StockMovement stockMovement, StockMovementUpdateRequestDto request) {
    super.prepare(stockMovement, request);
    stockMovement.quantity = request.getQuantity();
    stockMovement.reason = request.getReason();
  }
}
