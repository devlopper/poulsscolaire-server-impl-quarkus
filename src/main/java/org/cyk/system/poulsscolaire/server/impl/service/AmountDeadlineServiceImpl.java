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
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineDto;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineService;
import org.cyk.system.poulsscolaire.server.impl.business.amountdeadline.AmountDeadlineCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amountdeadline.AmountDeadlineDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amountdeadline.AmountDeadlineReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amountdeadline.AmountDeadlineReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amountdeadline.AmountDeadlineReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amountdeadline.AmountDeadlineUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link AmountDeadlineService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountDeadlineServiceImpl extends AbstractServiceImpl
    implements AmountDeadlineService {

  @Inject
  AmountDeadlineCreateBusiness createBusiness;

  @Inject
  AmountDeadlineReadManyBusiness readManyBusiness;

  @Inject
  AmountDeadlineReadOneBusiness readOneBusiness;

  @Inject
  AmountDeadlineReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AmountDeadlineUpdateBusiness updateBusiness;

  @Inject
  AmountDeadlineDeleteBusiness deleteBusiness;

  @Override
  public Response create(AmountDeadlineCreateRequestDto request) {
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
    AmountDeadlineDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    AmountDeadlineDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(AmountDeadlineUpdateRequestDto request) {
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
