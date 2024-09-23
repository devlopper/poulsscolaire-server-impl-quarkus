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
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountService;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation.AccountingOperationAccountCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation.AccountingOperationAccountDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation.AccountingOperationAccountReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation.AccountingOperationAccountReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation.AccountingOperationAccountReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation.AccountingOperationAccountUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link AccountingOperationAccountService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingOperationAccountServiceImpl extends AbstractServiceImpl
    implements AccountingOperationAccountService {

  @Inject
  AccountingOperationAccountCreateBusiness createBusiness;

  @Inject
  AccountingOperationAccountReadManyBusiness readManyBusiness;

  @Inject
  AccountingOperationAccountReadOneBusiness readOneBusiness;

  @Inject
  AccountingOperationAccountReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AccountingOperationAccountUpdateBusiness updateBusiness;

  @Inject
  AccountingOperationAccountDeleteBusiness deleteBusiness;

  @Override
  public Response create(AccountingOperationAccountCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    AccountingOperationAccountGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    AccountingOperationAccountDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    AccountingOperationAccountDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(AccountingOperationAccountUpdateRequestDto request) {
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
