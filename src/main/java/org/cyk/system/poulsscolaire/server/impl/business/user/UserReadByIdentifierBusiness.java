package org.cyk.system.poulsscolaire.server.impl.business.user;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.UserDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.User;
import org.cyk.system.poulsscolaire.server.impl.persistence.UserDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.UserPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link User}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class UserReadByIdentifierBusiness extends AbstractIdentifiableReadByIdentifierBusiness<
    User, UserPersistence, UserDynamicQuery, UserDto, UserMapper> {

  protected UserReadByIdentifierBusiness() {
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
