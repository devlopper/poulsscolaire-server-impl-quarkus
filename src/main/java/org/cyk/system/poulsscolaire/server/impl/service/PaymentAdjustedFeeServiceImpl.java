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
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeService;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link PaymentAdjustedFeeService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentAdjustedFeeServiceImpl extends AbstractServiceImpl
    implements PaymentAdjustedFeeService {

  @Inject
  PaymentAdjustedFeeCreateBusiness createBusiness;

  @Inject
  PaymentAdjustedFeeReadManyBusiness readManyBusiness;

  @Inject
  PaymentAdjustedFeeReadOneBusiness readOneBusiness;

  @Inject
  PaymentAdjustedFeeReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  PaymentAdjustedFeeUpdateBusiness updateBusiness;

  @Inject
  PaymentAdjustedFeeDeleteBusiness deleteBusiness;

  @Override
  public Response create(PaymentAdjustedFeeCreateRequestDto request) {
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
    PaymentAdjustedFeeDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    PaymentAdjustedFeeDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(PaymentAdjustedFeeUpdateRequestDto request) {
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
