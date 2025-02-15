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
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionService;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionUpdateSubsidiesBusiness;

/**
 * Cette classe représente l'implémentation de {@link SubsidyDecisionService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionServiceImpl extends AbstractServiceImpl
    implements SubsidyDecisionService {

  @Inject
  SubsidyDecisionCreateBusiness createBusiness;

  @Inject
  SubsidyDecisionReadManyBusiness readManyBusiness;

  @Inject
  SubsidyDecisionReadOneBusiness readOneBusiness;

  @Inject
  SubsidyDecisionReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  SubsidyDecisionUpdateBusiness updateBusiness;

  @Inject
  SubsidyDecisionUpdateSubsidiesBusiness updateSubsidiesBusiness;
  
  @Inject
  SubsidyDecisionDeleteBusiness deleteBusiness;

  @Override
  public Response create(SubsidyDecisionCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    SubsidyDecisionGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    SubsidyDecisionDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    SubsidyDecisionDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(SubsidyDecisionUpdateRequestDto request) {
    IdentifiableResponseDto dto = updateBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response updateSubsidies(SubsidyDecisionUpdateSubsidiesRequestDto request) {
    IdentifiableResponseDto dto = updateSubsidiesBusiness.process(request);
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
