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
import org.cyk.system.poulsscolaire.server.api.configuration.GenderDto;
import org.cyk.system.poulsscolaire.server.api.configuration.GenderService;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link GenderService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class GenderServiceImpl extends AbstractServiceImpl implements GenderService {

  @Inject
  GenderCreateBusiness createBusiness;
  
  @Inject
  GenderReadManyBusiness readManyBusiness;
  
  @Inject
  GenderReadOneBusiness readOneBusiness;
  
  @Inject
  GenderReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  GenderUpdateBusiness updateBusiness;
  
  @Inject
  GenderDeleteBusiness deleteBusiness;

  @Override
  public Response create(GenderCreateRequestDto request) {
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
    GenderDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    GenderDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(GenderUpdateRequestDto request) {
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
