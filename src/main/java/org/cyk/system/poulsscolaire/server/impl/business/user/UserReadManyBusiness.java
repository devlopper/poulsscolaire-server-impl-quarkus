package org.cyk.system.poulsscolaire.server.impl.business.user;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.UserDto;
import org.cyk.system.poulsscolaire.server.api.configuration.UserService.UserGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.User;
import org.cyk.system.poulsscolaire.server.impl.persistence.UserDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.UserPersistence;

/**
 * Cette classe représente l'obtention de {@link User}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class UserReadManyBusiness extends AbstractIdentifiableReadManyBusiness<User,
    UserPersistence, UserDynamicQuery, UserDto, UserMapper, UserGetManyResponseDto> {

  protected UserReadManyBusiness() {
    super(UserGetManyResponseDto.class);
  }

  @Inject
  @Getter
  UserPersistence persistence;

  @Inject
  @Getter
  UserDynamicQuery dynamicQuery;

  @Inject
  @Getter
  UserMapper mapper;
}
