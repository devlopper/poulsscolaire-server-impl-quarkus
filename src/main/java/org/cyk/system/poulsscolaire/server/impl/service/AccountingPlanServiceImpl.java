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
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanService;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link AccountingPlanService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingPlanServiceImpl extends AbstractServiceImpl
    implements AccountingPlanService {

  @Inject
  AccountingPlanCreateBusiness createBusiness;

  @Inject
  AccountingPlanReadManyBusiness readManyBusiness;

  @Inject
  AccountingPlanReadOneBusiness readOneBusiness;

  @Inject
  AccountingPlanReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AccountingPlanUpdateBusiness updateBusiness;

  @Inject
  AccountingPlanDeleteBusiness deleteBusiness;

  @Override
  public Response create(AccountingPlanCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    AccountingPlanGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    AccountingPlanDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    AccountingPlanDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(AccountingPlanUpdateRequestDto request) {
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
