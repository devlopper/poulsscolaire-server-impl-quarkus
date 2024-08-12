package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.UserDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link User}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class UserPersistence extends AbstractIdentifiablePersistence<User> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public UserPersistence() {
    super(User.class);
    name = UserDto.NAME;
    pluralName = UserDto.PLURAL_NAME;
  }
}
