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
import org.cyk.system.poulsscolaire.server.api.AssignmentTypeDto;
import org.cyk.system.poulsscolaire.server.api.AssignmentTypeService;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link AssignmentTypeService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AssignmentTypeServiceImpl extends AbstractServiceImpl
    implements AssignmentTypeService {

  @Inject
  AssignmentTypeCreateBusiness createBusiness;

  @Inject
  AssignmentTypeReadManyBusiness readManyBusiness;

  @Inject
  AssignmentTypeReadOneBusiness readOneBusiness;

  @Inject
  AssignmentTypeReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AssignmentTypeUpdateBusiness updateBusiness;

  @Inject
  AssignmentTypeDeleteBusiness deleteBusiness;

  @Override
  public Response create(AssignmentTypeCreateRequestDto request) {
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
    AssignmentTypeDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    AssignmentTypeDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(AssignmentTypeUpdateRequestDto request) {
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
