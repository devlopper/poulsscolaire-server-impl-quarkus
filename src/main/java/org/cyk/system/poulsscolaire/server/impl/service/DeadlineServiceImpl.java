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
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineDto;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineService;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link DeadlineService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineServiceImpl extends AbstractServiceImpl implements DeadlineService {

  @Inject
  DeadlineCreateBusiness createBusiness;
  
  @Inject
  DeadlineReadManyBusiness readManyBusiness;
  
  @Inject
  DeadlineReadOneBusiness readOneBusiness;
  
  @Inject
  DeadlineReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  DeadlineUpdateBusiness updateBusiness;
  
  @Inject
  DeadlineDeleteBusiness deleteBusiness;

  @Override
  public Response create(DeadlineCreateRequestDto request) {
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
    DeadlineDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    DeadlineDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(DeadlineUpdateRequestDto request) {
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
