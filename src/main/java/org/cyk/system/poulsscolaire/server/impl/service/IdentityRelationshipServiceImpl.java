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
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.identityrelationship.IdentityRelationshipUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link IdentityRelationshipService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityRelationshipServiceImpl extends AbstractServiceImpl
    implements IdentityRelationshipService {

  @Inject
  IdentityRelationshipCreateBusiness createBusiness;

  @Inject
  IdentityRelationshipReadManyBusiness readManyBusiness;

  @Inject
  IdentityRelationshipReadOneBusiness readOneBusiness;

  @Inject
  IdentityRelationshipReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  IdentityRelationshipUpdateBusiness updateBusiness;

  @Inject
  IdentityRelationshipDeleteBusiness deleteBusiness;

  @Override
  public Response create(IdentityRelationshipCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    IdentityRelationshipGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    IdentityRelationshipDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    IdentityRelationshipDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(IdentityRelationshipUpdateRequestDto request) {
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
