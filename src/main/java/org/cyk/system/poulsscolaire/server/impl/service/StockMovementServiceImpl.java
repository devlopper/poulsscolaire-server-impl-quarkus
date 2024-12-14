package org.cyk.system.poulsscolaire.server.impl.service;

import ci.gouv.dgbf.extension.core.ResponseBuilder;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetByIdentifierRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetOneRequestDto;
import ci.gouv.dgbf.extension.server.service.api.response.CreateResponseDto;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import ci.gouv.dgbf.extension.server.service.impl.AbstractServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementService;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link StockMovementService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockMovementServiceImpl extends AbstractServiceImpl implements StockMovementService {

  @Inject
  StockMovementCreateBusiness createBusiness;
  
  @Inject
  StockMovementReadManyBusiness readManyBusiness;
  
  @Inject
  StockMovementReadOneBusiness readOneBusiness;
  
  @Inject
  StockMovementReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  StockMovementUpdateBusiness updateBusiness;
  
  @Inject
  StockMovementDeleteBusiness deleteBusiness;

  @Override
  public Response create(StockMovementCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    StockMovementGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    StockMovementDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    StockMovementDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(StockMovementUpdateRequestDto request) {
    IdentifiableResponseDto dto = updateBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response delete(DeleteOneRequestDto request) {
    IdentifiableResponseDto dto = deleteBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
}
