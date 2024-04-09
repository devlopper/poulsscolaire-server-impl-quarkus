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
import org.cyk.system.poulsscolaire.server.api.fee.FeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeService;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link FeeService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeServiceImpl extends AbstractServiceImpl implements FeeService {

  @Inject
  FeeCreateBusiness createBusiness;
  
  @Inject
  FeeReadManyBusiness readManyBusiness;
  
  @Inject
  FeeReadOneBusiness readOneBusiness;
  
  @Inject
  FeeReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  FeeUpdateBusiness updateBusiness;
  
  @Inject
  FeeDeleteBusiness deleteBusiness;

  @Override
  public Response create(FeeCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    GetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    FeeDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    FeeDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(FeeUpdateRequestDto request) {
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
