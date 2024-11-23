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
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceService;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link FundingSourceService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingSourceServiceImpl extends AbstractServiceImpl implements FundingSourceService {

  @Inject
  FundingSourceCreateBusiness createBusiness;
  
  @Inject
  FundingSourceReadManyBusiness readManyBusiness;
  
  @Inject
  FundingSourceReadOneBusiness readOneBusiness;
  
  @Inject
  FundingSourceReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  FundingSourceUpdateBusiness updateBusiness;
  
  @Inject
  FundingSourceDeleteBusiness deleteBusiness;

  @Override
  public Response create(FundingSourceCreateRequestDto request) {
    CreateResponseDto dto = createBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    responseBuilder.setStatusCode(Status.CREATED.getStatusCode());
    return responseBuilder.build();
  }

  @Override
  public Response getMany(GetManyRequestDto request) {
    FundingSourceGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    FundingSourceDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    FundingSourceDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(FundingSourceUpdateRequestDto request) {
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
