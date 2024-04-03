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
import org.cyk.system.poulsscolaire.server.api.IdentityDto;
import org.cyk.system.poulsscolaire.server.api.IdentityService;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link IdentityService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityServiceImpl extends AbstractServiceImpl implements IdentityService {

  @Inject
  IdentityCreateBusiness createBusiness;
  
  @Inject
  IdentityReadManyBusiness readManyBusiness;
  
  @Inject
  IdentityReadOneBusiness readOneBusiness;
  
  @Inject
  IdentityReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  IdentityUpdateBusiness updateBusiness;
  
  @Inject
  IdentityDeleteBusiness deleteBusiness;

  @Override
  public Response create(IdentityCreateRequestDto request) {
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
    IdentityDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    IdentityDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(IdentityUpdateRequestDto request) {
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
