package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link SchoolUser}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolUserPersistence extends AbstractIdentifiablePersistence<SchoolUser> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public SchoolUserPersistence() {
    super(SchoolUser.class);
    name = "école d'utilisateur";
    pluralName = "écoles d'utilisateurs";
  }
}
