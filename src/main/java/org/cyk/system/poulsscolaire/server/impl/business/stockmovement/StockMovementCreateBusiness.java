package org.cyk.system.poulsscolaire.server.impl.business.stockmovement;

import ci.gouv.dgbf.extension.core.NumberHelper;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import ci.gouv.dgbf.extension.server.business.ResponseBuilder.Arguments;
import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementService.StockMovementCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementService.StockMovementCreateResponseDto;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Stock;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovement;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovementPersistence;

/**
 * Cette classe représente la création de {@link StockMovement}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockMovementCreateBusiness extends AbstractIdentifiableCreateBusiness<StockMovement,
    StockMovementPersistence, StockMovementValidator, StockMovementCreateRequestDto> {

  @Inject
  @Getter
  StockMovementPersistence persistence;

  @Inject
  @Getter
  StockMovementValidator validator;

  @Inject
  StockValidator stockValidator;

  @Inject
  NumberHelper numberHelper;

  @Override
  protected Object[] validate(StockMovementCreateRequestDto request, StringList messages) {
    Stock stock =
        stockValidator.validateInstanceByIdentifier(request.getStockIdentifier(), messages);
    validator.validateQuantity(request.getQuantity(), messages);
    return new Object[] {stock};
  }

  @Override
  protected void setFields(StockMovement stockMovement, Object[] array,
      StockMovementCreateRequestDto request) {
    super.setFields(stockMovement, array, request);
    stockMovement.stock = (Stock) array[0];
    stockMovement.quantity = request.getQuantity();
    stockMovement.reason = request.getReason();
  }

  @Override
  protected Class<? extends CreateResponseDto> getResponseClass() {
    return StockMovementCreateResponseDto.class;
  }

  @Override
  protected CreateResponseDto buildResponse(StockMovement stockMovement, Arguments arguments) {
    StockMovementCreateResponseDto response =
        (StockMovementCreateResponseDto) super.buildResponse(stockMovement, arguments);
    response.setStockQuantityAsString(
        numberHelper.format(persistence.sumQuantityByStock(stockMovement.stock)));
    return response;
  }
}
