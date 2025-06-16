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
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionRegistrationDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionRegistrationService;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration.StockDistributionRegistrationUpdateQuantityBusiness;

/**
 * Cette classe représente l'implémentation de {@link StockDistributionRegistrationService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionRegistrationServiceImpl extends AbstractServiceImpl
    implements StockDistributionRegistrationService {

  @Inject
  StockDistributionRegistrationCreateBusiness createBusiness;

  @Inject
  StockDistributionRegistrationReadManyBusiness readManyBusiness;

  @Inject
  StockDistributionRegistrationReadOneBusiness readOneBusiness;

  @Inject
  StockDistributionRegistrationReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  StockDistributionRegistrationUpdateBusiness updateBusiness;

  @Inject
  StockDistributionRegistrationUpdateQuantityBusiness updateQuantityBusiness;

  @Inject
  StockDistributionRegistrationDeleteBusiness deleteBusiness;

  @Override
  public Response create(StockDistributionRegistrationCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    StockDistributionRegistrationGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    StockDistributionRegistrationDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    StockDistributionRegistrationDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(StockDistributionRegistrationUpdateRequestDto request) {
    IdentifiableResponseDto dto = updateBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response updateQuantity(StockDistributionRegistrationUpdateQuantityRequestDto request) {
    IdentifiableResponseDto dto = updateQuantityBusiness.process(request);
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
