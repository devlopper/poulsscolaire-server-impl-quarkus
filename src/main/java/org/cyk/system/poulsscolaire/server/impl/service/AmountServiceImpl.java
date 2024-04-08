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
import org.cyk.system.poulsscolaire.server.api.fee.AmountDto;
import org.cyk.system.poulsscolaire.server.api.fee.AmountService;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.amount.AmountUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link AmountService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountServiceImpl extends AbstractServiceImpl implements AmountService {

  @Inject
  AmountCreateBusiness createBusiness;
  
  @Inject
  AmountReadManyBusiness readManyBusiness;
  
  @Inject
  AmountReadOneBusiness readOneBusiness;
  
  @Inject
  AmountReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  AmountUpdateBusiness updateBusiness;
  
  @Inject
  AmountDeleteBusiness deleteBusiness;

  @Override
  public Response create(AmountCreateRequestDto request) {
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
    AmountDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    AmountDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(AmountUpdateRequestDto request) {
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
