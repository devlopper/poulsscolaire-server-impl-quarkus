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
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentService;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment.SubsidyDecisionPaymentCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment.SubsidyDecisionPaymentDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment.SubsidyDecisionPaymentReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment.SubsidyDecisionPaymentReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment.SubsidyDecisionPaymentReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment.SubsidyDecisionPaymentUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link SubsidyDecisionPaymentService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionPaymentServiceImpl extends AbstractServiceImpl
    implements SubsidyDecisionPaymentService {

  @Inject
  SubsidyDecisionPaymentCreateBusiness createBusiness;

  @Inject
  SubsidyDecisionPaymentReadManyBusiness readManyBusiness;

  @Inject
  SubsidyDecisionPaymentReadOneBusiness readOneBusiness;

  @Inject
  SubsidyDecisionPaymentReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  SubsidyDecisionPaymentUpdateBusiness updateBusiness;

  @Inject
  SubsidyDecisionPaymentDeleteBusiness deleteBusiness;

  @Override
  public Response create(SubsidyDecisionPaymentCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    SubsidyDecisionPaymentGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    SubsidyDecisionPaymentDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    SubsidyDecisionPaymentDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(SubsidyDecisionPaymentUpdateRequestDto request) {
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
