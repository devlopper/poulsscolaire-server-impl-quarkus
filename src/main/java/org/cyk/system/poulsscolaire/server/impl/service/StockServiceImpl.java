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
import org.cyk.system.poulsscolaire.server.api.fee.StockDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockService;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link StockService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockServiceImpl extends AbstractServiceImpl implements StockService {

  @Inject
  StockCreateBusiness createBusiness;
  
  @Inject
  StockReadManyBusiness readManyBusiness;
  
  @Inject
  StockReadOneBusiness readOneBusiness;
  
  @Inject
  StockReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  StockUpdateBusiness updateBusiness;
  
  @Inject
  StockDeleteBusiness deleteBusiness;

  @Override
  public Response create(StockCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    StockGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    StockDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    StockDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(StockUpdateRequestDto request) {
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
