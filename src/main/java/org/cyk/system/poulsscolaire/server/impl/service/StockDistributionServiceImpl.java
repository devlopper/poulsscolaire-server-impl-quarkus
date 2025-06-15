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
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionService;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistribution.StockDistributionUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link StockDistributionService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionServiceImpl extends AbstractServiceImpl
    implements StockDistributionService {

  @Inject
  StockDistributionCreateBusiness createBusiness;

  @Inject
  StockDistributionReadManyBusiness readManyBusiness;

  @Inject
  StockDistributionReadOneBusiness readOneBusiness;

  @Inject
  StockDistributionReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  StockDistributionUpdateBusiness updateBusiness;

  @Inject
  StockDistributionDeleteBusiness deleteBusiness;

  @Override
  public Response create(StockDistributionCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    StockDistributionGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    StockDistributionDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    StockDistributionDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(StockDistributionUpdateRequestDto request) {
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
