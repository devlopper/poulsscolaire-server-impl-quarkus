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
import org.cyk.system.poulsscolaire.server.api.registration.StudentDto;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link StudentService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StudentServiceImpl extends AbstractServiceImpl implements StudentService {

  @Inject
  StudentCreateBusiness createBusiness;
  
  @Inject
  StudentReadManyBusiness readManyBusiness;
  
  @Inject
  StudentReadOneBusiness readOneBusiness;
  
  @Inject
  StudentReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  StudentUpdateBusiness updateBusiness;
  
  @Inject
  StudentDeleteBusiness deleteBusiness;

  @Override
  public Response create(StudentCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    StudentGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    StudentDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    StudentDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(StudentUpdateRequestDto request) {
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
