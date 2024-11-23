package org.cyk.system.poulsscolaire.server.impl.service;

import ci.gouv.dgbf.extension.core.ResponseBuilder;
import ci.gouv.dgbf.extension.server.service.api.request.GetByIdentifierRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetOneRequestDto;
import ci.gouv.dgbf.extension.server.service.impl.AbstractServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.cyk.system.poulsscolaire.server.api.configuration.DepartmentDto;
import org.cyk.system.poulsscolaire.server.api.configuration.DepartmentService;
import org.cyk.system.poulsscolaire.server.impl.business.department.DepartmentReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.department.DepartmentReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.department.DepartmentReadOneBusiness;

/**
 * Cette classe représente l'implémentation de {@link DepartmentService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DepartmentServiceImpl extends AbstractServiceImpl implements DepartmentService {

  @Inject
  DepartmentReadManyBusiness readManyBusiness;

  @Inject
  DepartmentReadOneBusiness readOneBusiness;

  @Inject
  DepartmentReadByIdentifierBusiness readByIdentifierBusiness;

  @Override
  public Response getMany(GetManyRequestDto request) {
    DepartmentGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    DepartmentDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    DepartmentDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
}
