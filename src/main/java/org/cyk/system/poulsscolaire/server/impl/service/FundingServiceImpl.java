package org.cyk.system.poulsscolaire.server.impl.service;

import ci.gouv.dgbf.extension.core.ResponseBuilder;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
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
import org.cyk.system.poulsscolaire.server.api.accounting.FundingDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingAcceptBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingApproveBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReturnBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingTransmitBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingUpdateAmountBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link FundingService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingServiceImpl extends AbstractServiceImpl
    implements FundingService {

  @Inject
  FundingCreateBusiness createBusiness;

  @Inject
  FundingTransmitBusiness transmitBusiness;
  
  @Inject
  FundingAcceptBusiness acceptBusiness;
  
  @Inject
  FundingApproveBusiness approveBusiness;
  
  @Inject
  FundingReturnBusiness returnBusiness;
  
  @Inject
  FundingReadManyBusiness readManyBusiness;

  @Inject
  FundingReadOneBusiness readOneBusiness;

  @Inject
  FundingReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  FundingUpdateBusiness updateBusiness;
  
  @Inject
  FundingUpdateAmountBusiness updateAmountBusiness;
  
  @Inject
  FundingDeleteBusiness deleteBusiness;

  @Override
  public Response create(FundingCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }
  
  @Override
  public Response transmit(ByIdentifierRequestDto request) {
    IdentifiableResponseDto dto = transmitBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response accept(ByIdentifierRequestDto request) {
    IdentifiableResponseDto dto = acceptBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response approve(ByIdentifierRequestDto request) {
    IdentifiableResponseDto dto = approveBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response returnBack(FundingReturnRequestDto request) {
    IdentifiableResponseDto dto = returnBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    FundingGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    FundingDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    FundingDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(FundingUpdateRequestDto request) {
    IdentifiableResponseDto dto = updateBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response updateAmount(FundingUpdateAmountRequestDto request) {
    IdentifiableResponseDto dto = updateAmountBusiness.process(request);
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
