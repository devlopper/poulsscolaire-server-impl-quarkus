package org.cyk.system.poulsscolaire.server.impl.service;

import ci.gouv.dgbf.extension.core.ResponseBuilder;
import ci.gouv.dgbf.extension.server.service.api.request.GetByIdentifierRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetOneRequestDto;
import ci.gouv.dgbf.extension.server.service.impl.AbstractServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchService;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadOneBusiness;

/**
 * Cette classe représente l'implémentation de {@link BranchService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchServiceImpl extends AbstractServiceImpl implements BranchService {

  @Inject
  BranchReadManyBusiness readManyBusiness;

  @Inject
  BranchReadOneBusiness readOneBusiness;

  @Inject
  BranchReadByIdentifierBusiness readByIdentifierBusiness;

  @Override
  public Response getMany(GetManyRequestDto request) {
    BranchGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    BranchDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    BranchDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
}
