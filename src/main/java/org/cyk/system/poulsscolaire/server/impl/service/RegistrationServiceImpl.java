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
import org.cyk.system.poulsscolaire.server.api.RegistrationDto;
import org.cyk.system.poulsscolaire.server.api.RegistrationService;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link RegistrationService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class RegistrationServiceImpl extends AbstractServiceImpl implements RegistrationService {

  @Inject
  RegistrationCreateBusiness createBusiness;
  
  @Inject
  RegistrationReadManyBusiness readManyBusiness;
  
  @Inject
  RegistrationReadOneBusiness readOneBusiness;
  
  @Inject
  RegistrationReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  RegistrationUpdateBusiness updateBusiness;
  
  @Inject
  RegistrationDeleteBusiness deleteBusiness;

  @Override
  public Response create(RegistrationCreateRequestDto request) {
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
    RegistrationDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    RegistrationDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(RegistrationUpdateRequestDto request) {
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
