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
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationService;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration.SubsidyDecisionRegistrationCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration.SubsidyDecisionRegistrationDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration.SubsidyDecisionRegistrationReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration.SubsidyDecisionRegistrationReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration.SubsidyDecisionRegistrationReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration.SubsidyDecisionRegistrationUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link SubsidyDecisionRegistrationService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionRegistrationServiceImpl extends AbstractServiceImpl
    implements SubsidyDecisionRegistrationService {

  @Inject
  SubsidyDecisionRegistrationCreateBusiness createBusiness;

  @Inject
  SubsidyDecisionRegistrationReadManyBusiness readManyBusiness;

  @Inject
  SubsidyDecisionRegistrationReadOneBusiness readOneBusiness;

  @Inject
  SubsidyDecisionRegistrationReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  SubsidyDecisionRegistrationUpdateBusiness updateBusiness;

  @Inject
  SubsidyDecisionRegistrationDeleteBusiness deleteBusiness;

  @Override
  public Response create(SubsidyDecisionRegistrationCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    SubsidyDecisionRegistrationGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    SubsidyDecisionRegistrationDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    SubsidyDecisionRegistrationDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(SubsidyDecisionRegistrationUpdateRequestDto request) {
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
