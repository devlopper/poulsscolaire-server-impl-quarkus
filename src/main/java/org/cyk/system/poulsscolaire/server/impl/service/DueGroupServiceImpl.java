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
import org.cyk.system.poulsscolaire.server.api.DueGroupDto;
import org.cyk.system.poulsscolaire.server.api.DueGroupService;
import org.cyk.system.poulsscolaire.server.impl.business.duegroup.DueGroupCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.duegroup.DueGroupDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.duegroup.DueGroupReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.duegroup.DueGroupReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.duegroup.DueGroupReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.duegroup.DueGroupUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link DueGroupService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DueGroupServiceImpl extends AbstractServiceImpl implements DueGroupService {

  @Inject
  DueGroupCreateBusiness createBusiness;
  
  @Inject
  DueGroupReadManyBusiness readManyBusiness;
  
  @Inject
  DueGroupReadOneBusiness readOneBusiness;
  
  @Inject
  DueGroupReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  DueGroupUpdateBusiness updateBusiness;
  
  @Inject
  DueGroupDeleteBusiness deleteBusiness;

  @Override
  public Response create(DueGroupCreateRequestDto request) {
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
    DueGroupDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    DueGroupDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(DueGroupUpdateRequestDto request) {
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
