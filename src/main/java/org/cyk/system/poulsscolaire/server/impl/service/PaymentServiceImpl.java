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
import org.cyk.system.poulsscolaire.server.api.payment.PaymentDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentService;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.payment.PaymentUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link PaymentService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentServiceImpl extends AbstractServiceImpl implements PaymentService {

  @Inject
  PaymentCreateBusiness createBusiness;
  
  @Inject
  PaymentReadManyBusiness readManyBusiness;
  
  @Inject
  PaymentReadOneBusiness readOneBusiness;
  
  @Inject
  PaymentReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  PaymentUpdateBusiness updateBusiness;
  
  @Inject
  PaymentDeleteBusiness deleteBusiness;

  @Override
  public Response create(PaymentCreateRequestDto request) {
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
    PaymentDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    PaymentDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(PaymentUpdateRequestDto request) {
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
