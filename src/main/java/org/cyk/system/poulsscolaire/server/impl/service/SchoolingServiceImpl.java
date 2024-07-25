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
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingGenerateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link SchoolingService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolingServiceImpl extends AbstractServiceImpl implements SchoolingService {

  @Inject
  SchoolingCreateBusiness createBusiness;

  @Inject
  SchoolingReadManyBusiness readManyBusiness;

  @Inject
  SchoolingReadOneBusiness readOneBusiness;

  @Inject
  SchoolingReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  SchoolingUpdateBusiness updateBusiness;

  @Inject
  SchoolingDeleteBusiness deleteBusiness;

  @Inject
  SchoolingGenerateBusiness generateBusiness;
  
  @Override
  public Response create(SchoolingCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    SchoolingGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    SchoolingDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    SchoolingDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(SchoolingUpdateRequestDto request) {
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

  @Override
  public Response generate(SchoolingGenerateRequestDto request) {
    SchoolingGenerateResponseDto dto = generateBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
}
