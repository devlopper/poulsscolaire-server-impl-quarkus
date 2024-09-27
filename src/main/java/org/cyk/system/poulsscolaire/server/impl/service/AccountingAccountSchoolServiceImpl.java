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
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolService;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link AccountingAccountSchoolService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AccountingAccountSchoolServiceImpl extends AbstractServiceImpl
    implements AccountingAccountSchoolService {

  @Inject
  AccountingAccountSchoolCreateBusiness createBusiness;

  @Inject
  AccountingAccountSchoolReadManyBusiness readManyBusiness;

  @Inject
  AccountingAccountSchoolReadOneBusiness readOneBusiness;

  @Inject
  AccountingAccountSchoolReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AccountingAccountSchoolUpdateBusiness updateBusiness;

  @Inject
  AccountingAccountSchoolDeleteBusiness deleteBusiness;

  @Override
  public Response create(AccountingAccountSchoolCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    AccountingAccountSchoolGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    AccountingAccountSchoolDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    AccountingAccountSchoolDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(AccountingAccountSchoolUpdateRequestDto request) {
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
