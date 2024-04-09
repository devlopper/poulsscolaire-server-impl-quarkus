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
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeService;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfee.AdjustedFeeUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link AdjustedFeeService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeeServiceImpl extends AbstractServiceImpl implements AdjustedFeeService {

  @Inject
  AdjustedFeeCreateBusiness createBusiness;
  
  @Inject
  AdjustedFeeReadManyBusiness readManyBusiness;
  
  @Inject
  AdjustedFeeReadOneBusiness readOneBusiness;
  
  @Inject
  AdjustedFeeReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  AdjustedFeeUpdateBusiness updateBusiness;
  
  @Inject
  AdjustedFeeDeleteBusiness deleteBusiness;

  @Override
  public Response create(AdjustedFeeCreateRequestDto request) {
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
    AdjustedFeeDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    AdjustedFeeDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(AdjustedFeeUpdateRequestDto request) {
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
