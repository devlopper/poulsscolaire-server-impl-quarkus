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
import org.cyk.system.poulsscolaire.server.api.BranchDto;
import org.cyk.system.poulsscolaire.server.api.BranchService;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link BranchService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchServiceImpl extends AbstractServiceImpl implements BranchService {

  @Inject
  BranchCreateBusiness createBusiness;
  
  @Inject
  BranchReadManyBusiness readManyBusiness;
  
  @Inject
  BranchReadOneBusiness readOneBusiness;
  
  @Inject
  BranchReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  BranchUpdateBusiness updateBusiness;
  
  @Inject
  BranchDeleteBusiness deleteBusiness;

  @Override
  public Response create(BranchCreateRequestDto request) {
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

  @Override
  public Response update(BranchUpdateRequestDto request) {
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
