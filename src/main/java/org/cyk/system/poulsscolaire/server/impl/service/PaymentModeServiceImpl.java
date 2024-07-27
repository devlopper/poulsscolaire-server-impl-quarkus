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
import org.cyk.system.poulsscolaire.server.api.payment.PaymentModeDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentModeService;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link PaymentModeService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentModeServiceImpl extends AbstractServiceImpl implements PaymentModeService {

  @Inject
  PaymentModeCreateBusiness createBusiness;
  
  @Inject
  PaymentModeReadManyBusiness readManyBusiness;
  
  @Inject
  PaymentModeReadOneBusiness readOneBusiness;
  
  @Inject
  PaymentModeReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  PaymentModeUpdateBusiness updateBusiness;
  
  @Inject
  PaymentModeDeleteBusiness deleteBusiness;

  @Override
  public Response create(PaymentModeCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    PaymentModeGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    PaymentModeDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    PaymentModeDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(PaymentModeUpdateRequestDto request) {
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
