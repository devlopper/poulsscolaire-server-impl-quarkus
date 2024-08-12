package org.cyk.system.poulsscolaire.server.impl.service;

import ci.gouv.dgbf.extension.core.ResponseBuilder;
import ci.gouv.dgbf.extension.server.service.api.request.GetByIdentifierRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetOneRequestDto;
import ci.gouv.dgbf.extension.server.service.impl.AbstractServiceImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.cyk.system.poulsscolaire.server.api.configuration.UserDto;
import org.cyk.system.poulsscolaire.server.api.configuration.UserService;
import org.cyk.system.poulsscolaire.server.impl.business.user.UserReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.user.UserReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.user.UserReadOneBusiness;

/**
 * Cette classe représente l'implémentation de {@link UserService}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class UserServiceImpl extends AbstractServiceImpl implements UserService {

  @Inject
  UserReadManyBusiness readManyBusiness;

  @Inject
  UserReadOneBusiness readOneBusiness;

  @Inject
  UserReadByIdentifierBusiness readByIdentifierBusiness;

  @Override
  public Response getMany(GetManyRequestDto request) {
    UserGetManyResponseDto dto = readManyBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getOne(GetOneRequestDto request) {
    UserDto dto = readOneBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }

  @Override
  public Response getByIdentifier(GetByIdentifierRequestDto request) {
    UserDto dto = readByIdentifierBusiness.process(request);
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setDto(dto);
    return responseBuilder.build();
  }
}
