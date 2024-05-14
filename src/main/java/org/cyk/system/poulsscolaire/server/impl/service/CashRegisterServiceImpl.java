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
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterDto;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterService;
import org.cyk.system.poulsscolaire.server.impl.business.cashregister.CashRegisterCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.cashregister.CashRegisterDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.cashregister.CashRegisterReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.cashregister.CashRegisterReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.cashregister.CashRegisterReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.cashregister.CashRegisterUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link CashRegisterService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class CashRegisterServiceImpl extends AbstractServiceImpl implements CashRegisterService {

  @Inject
  CashRegisterCreateBusiness createBusiness;
  
  @Inject
  CashRegisterReadManyBusiness readManyBusiness;
  
  @Inject
  CashRegisterReadOneBusiness readOneBusiness;
  
  @Inject
  CashRegisterReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  CashRegisterUpdateBusiness updateBusiness;
  
  @Inject
  CashRegisterDeleteBusiness deleteBusiness;

  @Override
  public Response create(CashRegisterCreateRequestDto request) {
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
    CashRegisterDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    CashRegisterDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(CashRegisterUpdateRequestDto request) {
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
