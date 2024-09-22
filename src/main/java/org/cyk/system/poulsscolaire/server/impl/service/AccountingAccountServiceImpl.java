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
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountService;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link AccountingAccountService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountServiceImpl extends AbstractServiceImpl
    implements AccountingAccountService {

  @Inject
  AccountingAccountCreateBusiness createBusiness;

  @Inject
  AccountingAccountReadManyBusiness readManyBusiness;

  @Inject
  AccountingAccountReadOneBusiness readOneBusiness;

  @Inject
  AccountingAccountReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AccountingAccountUpdateBusiness updateBusiness;

  @Inject
  AccountingAccountDeleteBusiness deleteBusiness;

  @Override
  public Response create(AccountingAccountCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    AccountingAccountGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    AccountingAccountDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    AccountingAccountDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(AccountingAccountUpdateRequestDto request) {
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
