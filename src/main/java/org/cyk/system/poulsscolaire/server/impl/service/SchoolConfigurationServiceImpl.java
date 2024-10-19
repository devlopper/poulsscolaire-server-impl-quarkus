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
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationService;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration.SchoolConfigurationUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link SchoolConfigurationService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolConfigurationServiceImpl extends AbstractServiceImpl
    implements SchoolConfigurationService {

  @Inject
  SchoolConfigurationCreateBusiness createBusiness;

  @Inject
  SchoolConfigurationReadManyBusiness readManyBusiness;

  @Inject
  SchoolConfigurationReadOneBusiness readOneBusiness;

  @Inject
  SchoolConfigurationReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  SchoolConfigurationUpdateBusiness updateBusiness;

  @Inject
  SchoolConfigurationDeleteBusiness deleteBusiness;

  @Override
  public Response create(SchoolConfigurationCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    SchoolConfigurationGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    SchoolConfigurationDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    SchoolConfigurationDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(SchoolConfigurationUpdateRequestDto request) {
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
