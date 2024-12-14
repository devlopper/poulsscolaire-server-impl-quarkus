package org.cyk.system.poulsscolaire.server.impl.business.stockmovement;

import ci.gouv.dgbf.extension.core.NumberHelper;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.business.ResponseBuilder.Arguments;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementService.StockMovementDeleteResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovement;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovementPersistence;

/**
 * Cette classe représente la suppression de {@link StockMovement}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockMovementDeleteBusiness extends AbstractIdentifiableDeleteBusiness<StockMovement,
    StockMovementPersistence, StockMovementValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  StockMovementPersistence persistence;

  @Inject
  @Getter
  StockMovementValidator validator;

  @Inject
  NumberHelper numberHelper;

  @Override
  protected Class<? extends IdentifiableResponseDto> getResponseClass() {
    return StockMovementDeleteResponseDto.class;
  }

  @Override
  protected IdentifiableResponseDto buildResponse(StockMovement stockMovement,
      Arguments arguments) {
    StockMovementDeleteResponseDto response =
        (StockMovementDeleteResponseDto) super.buildResponse(stockMovement, arguments);
    response.setStockQuantityAsString(
        numberHelper.format(persistence.sumQuantityByStock(stockMovement.stock)));
    return response;
  }
}
