package org.cyk.system.poulsscolaire.server.impl.service;

import ci.gouv.dgbf.extension.core.ResponseBuilder;
import ci.gouv.dgbf.extension.server.service.api.request.GetByIdentifierRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetOneRequestDto;
import ci.gouv.dgbf.extension.server.service.impl.AbstractServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodDto;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodService;
import org.cyk.system.poulsscolaire.server.impl.business.period.PeriodReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.period.PeriodReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.period.PeriodReadOneBusiness;

/**
 * Cette classe représente l'implémentation de {@link PeriodService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PeriodServiceImpl extends AbstractServiceImpl implements PeriodService {

  @Inject
  PeriodReadManyBusiness readManyBusiness;

  @Inject
  PeriodReadOneBusiness readOneBusiness;

  @Inject
  PeriodReadByIdentifierBusiness readByIdentifierBusiness;

  @Override
  public Response getMany(GetManyRequestDto request) {
    PeriodGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    PeriodDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    PeriodDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
}
