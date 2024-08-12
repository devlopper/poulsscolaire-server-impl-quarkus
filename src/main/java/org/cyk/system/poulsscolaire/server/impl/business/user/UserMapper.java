package org.cyk.system.poulsscolaire.server.impl.business.user;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.configuration.UserDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.User;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link User} et {@link UserDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface UserMapper extends IdentifiableMapper<User, UserDto> {
  
}
