package org.cyk.system.poulsscolaire.server.impl.business.user;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.UserDto;
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
public class UserReadOneBusiness extends AbstractIdentifiableReadOneBusiness<User,
    UserPersistence, UserDynamicQuery, UserDto, UserMapper> {

  protected UserReadOneBusiness() {
    super(UserDto.class);
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
