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
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationCancelBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperation.AccountingOperationUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link AccountingOperationService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationServiceImpl extends AbstractServiceImpl
    implements AccountingOperationService {

  @Inject
  AccountingOperationCreateBusiness createBusiness;

  @Inject
  AccountingOperationReadManyBusiness readManyBusiness;

  @Inject
  AccountingOperationReadOneBusiness readOneBusiness;

  @Inject
  AccountingOperationReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AccountingOperationUpdateBusiness updateBusiness;

  @Inject
  AccountingOperationCancelBusiness cancelBusiness;
  
  @Inject
  AccountingOperationDeleteBusiness deleteBusiness;

  @Override
  public Response create(AccountingOperationCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    AccountingOperationGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    AccountingOperationDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    AccountingOperationDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(AccountingOperationUpdateRequestDto request) {
    IdentifiableResponseDto dto = updateBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response cancel(ByIdentifierRequestDto request) {
    IdentifiableResponseDto dto = cancelBusiness.process(request);
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
