package org.cyk.system.poulsscolaire.server.impl.service;

import ci.gouv.dgbf.extension.core.ResponseBuilder;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
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
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetAcceptBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetApproveBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetReturnBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetTransmitBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budget.BudgetUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link BudgetService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetServiceImpl extends AbstractServiceImpl
    implements BudgetService {

  @Inject
  BudgetCreateBusiness createBusiness;

  @Inject
  BudgetTransmitBusiness transmitBusiness;
  
  @Inject
  BudgetAcceptBusiness acceptBusiness;
  
  @Inject
  BudgetApproveBusiness approveBusiness;
  
  @Inject
  BudgetReturnBusiness returnBusiness;
  
  @Inject
  BudgetReadManyBusiness readManyBusiness;

  @Inject
  BudgetReadOneBusiness readOneBusiness;

  @Inject
  BudgetReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  BudgetUpdateBusiness updateBusiness;
  
  @Inject
  BudgetDeleteBusiness deleteBusiness;

  @Override
  public Response create(BudgetCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }
  
  @Override
  public Response transmit(ByIdentifierRequestDto request) {
    IdentifiableResponseDto dto = transmitBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response accept(ByIdentifierRequestDto request) {
    IdentifiableResponseDto dto = acceptBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response approve(ByIdentifierRequestDto request) {
    IdentifiableResponseDto dto = approveBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response returnBack(BudgetReturnRequestDto request) {
    IdentifiableResponseDto dto = returnBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    BudgetGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    BudgetDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    BudgetDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(BudgetUpdateRequestDto request) {
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
