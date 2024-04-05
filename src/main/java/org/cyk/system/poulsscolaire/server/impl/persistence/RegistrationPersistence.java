package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Registration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class RegistrationPersistence
    extends AbstractIdentifiableCodablePersistence<Registration> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public RegistrationPersistence() {
    super(Registration.class);
    name = "inscription";
    pluralName = "inscriptions";
  }
}
