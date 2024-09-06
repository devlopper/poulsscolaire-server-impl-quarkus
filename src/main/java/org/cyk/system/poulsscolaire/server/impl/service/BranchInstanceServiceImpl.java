package org.cyk.system.poulsscolaire.server.impl.service;

import ci.gouv.dgbf.extension.core.ResponseBuilder;
import ci.gouv.dgbf.extension.server.service.api.request.GetByIdentifierRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetOneRequestDto;
import ci.gouv.dgbf.extension.server.service.impl.AbstractServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceService;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceReadOneBusiness;

/**
 * Cette classe représente l'implémentation de {@link BranchInstanceService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchInstanceServiceImpl extends AbstractServiceImpl
    implements BranchInstanceService {

  @Inject
  BranchInstanceReadManyBusiness readManyBusiness;

  @Inject
  BranchInstanceReadOneBusiness readOneBusiness;

  @Inject
  BranchInstanceReadByIdentifierBusiness readByIdentifierBusiness;

  @Override
  public Response getMany(GetManyRequestDto request) {
    BranchInstanceGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    BranchInstanceDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    BranchInstanceDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
}
