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
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineService;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline.AdjustedFeePaymentDeadlineCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline.AdjustedFeePaymentDeadlineDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline.AdjustedFeePaymentDeadlineReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline.AdjustedFeePaymentDeadlineReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline.AdjustedFeePaymentDeadlineReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline.AdjustedFeePaymentDeadlineUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link AdjustedFeePaymentDeadlineService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeePaymentDeadlineServiceImpl extends AbstractServiceImpl
    implements AdjustedFeePaymentDeadlineService {

  @Inject
  AdjustedFeePaymentDeadlineCreateBusiness createBusiness;

  @Inject
  AdjustedFeePaymentDeadlineReadManyBusiness readManyBusiness;

  @Inject
  AdjustedFeePaymentDeadlineReadOneBusiness readOneBusiness;

  @Inject
  AdjustedFeePaymentDeadlineReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AdjustedFeePaymentDeadlineUpdateBusiness updateBusiness;

  @Inject
  AdjustedFeePaymentDeadlineDeleteBusiness deleteBusiness;

  @Override
  public Response create(AdjustedFeePaymentDeadlineCreateRequestDto request) {
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
    AdjustedFeePaymentDeadlineDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    AdjustedFeePaymentDeadlineDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(AdjustedFeePaymentDeadlineUpdateRequestDto request) {
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
