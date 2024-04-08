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
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityService;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link SeniorityService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SeniorityServiceImpl extends AbstractServiceImpl implements SeniorityService {

  @Inject
  SeniorityCreateBusiness createBusiness;
  
  @Inject
  SeniorityReadManyBusiness readManyBusiness;
  
  @Inject
  SeniorityReadOneBusiness readOneBusiness;
  
  @Inject
  SeniorityReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  SeniorityUpdateBusiness updateBusiness;
  
  @Inject
  SeniorityDeleteBusiness deleteBusiness;

  @Override
  public Response create(SeniorityCreateRequestDto request) {
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
    SeniorityDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    SeniorityDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(SeniorityUpdateRequestDto request) {
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
