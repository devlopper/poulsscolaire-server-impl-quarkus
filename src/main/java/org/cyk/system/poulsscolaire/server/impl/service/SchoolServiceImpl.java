package org.cyk.system.poulsscolaire.server.impl.service;

import ci.gouv.dgbf.extension.core.ResponseBuilder;
import ci.gouv.dgbf.extension.server.service.api.request.GetByIdentifierRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetOneRequestDto;
import ci.gouv.dgbf.extension.server.service.impl.AbstractServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolService;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.school.SchoolRepatriateBusiness;

/**
 * Cette classe représente l'implémentation de {@link SchoolService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolServiceImpl extends AbstractServiceImpl implements SchoolService {

  @Inject
  SchoolRepatriateBusiness repatriateBusiness;

  @Inject
  SchoolReadManyBusiness readManyBusiness;

  @Inject
  SchoolReadOneBusiness readOneBusiness;

  @Inject
  SchoolReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Override
  public Response repatriate(SchoolRepatriateRequestDto request) {
    SchoolRepatriateResponseDto dto = repatriateBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    SchoolGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    SchoolDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    SchoolDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
}
