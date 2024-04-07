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
import org.cyk.system.poulsscolaire.server.api.DeadlineGroupDto;
import org.cyk.system.poulsscolaire.server.api.DeadlineGroupService;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup.DeadlineGroupUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link DeadlineGroupService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineGroupServiceImpl extends AbstractServiceImpl implements DeadlineGroupService {

  @Inject
  DeadlineGroupCreateBusiness createBusiness;
  
  @Inject
  DeadlineGroupReadManyBusiness readManyBusiness;
  
  @Inject
  DeadlineGroupReadOneBusiness readOneBusiness;
  
  @Inject
  DeadlineGroupReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  DeadlineGroupUpdateBusiness updateBusiness;
  
  @Inject
  DeadlineGroupDeleteBusiness deleteBusiness;

  @Override
  public Response create(DeadlineGroupCreateRequestDto request) {
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
    DeadlineGroupDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    DeadlineGroupDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(DeadlineGroupUpdateRequestDto request) {
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
