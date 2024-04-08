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
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryService;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryUpdateBusiness;

/**
 * Cette classe représente l'implémentation de {@link FeeCategoryService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeCategoryServiceImpl extends AbstractServiceImpl implements FeeCategoryService {

  @Inject
  FeeCategoryCreateBusiness createBusiness;
  
  @Inject
  FeeCategoryReadManyBusiness readManyBusiness;
  
  @Inject
  FeeCategoryReadOneBusiness readOneBusiness;
  
  @Inject
  FeeCategoryReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  FeeCategoryUpdateBusiness updateBusiness;
  
  @Inject
  FeeCategoryDeleteBusiness deleteBusiness;

  @Override
  public Response create(FeeCategoryCreateRequestDto request) {
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
    FeeCategoryDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
  
  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    FeeCategoryDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response update(FeeCategoryUpdateRequestDto request) {
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
