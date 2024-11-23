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
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineService;
import org.cyk.system.poulsscolaire.server.impl.business.budgetline.BudgetLineCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budgetline.BudgetLineDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budgetline.BudgetLineReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budgetline.BudgetLineReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budgetline.BudgetLineReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.budgetline.BudgetLineUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link BudgetLineService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetLineServiceImpl extends AbstractServiceImpl
    implements BudgetLineService {

  @Inject
  BudgetLineCreateBusiness createBusiness;

  @Inject
  BudgetLineReadManyBusiness readManyBusiness;

  @Inject
  BudgetLineReadOneBusiness readOneBusiness;

  @Inject
  BudgetLineReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  BudgetLineUpdateBusiness updateBusiness;
  
  @Inject
  BudgetLineDeleteBusiness deleteBusiness;

  @Override
  public Response create(BudgetLineCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    BudgetLineGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    BudgetLineDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    BudgetLineDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(BudgetLineUpdateRequestDto request) {
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
