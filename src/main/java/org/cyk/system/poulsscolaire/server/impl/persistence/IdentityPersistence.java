package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Identity}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityPersistence extends AbstractIdentifiablePersistence<Identity> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public IdentityPersistence() {
    super(Identity.class);
    name = "identité";
    pluralName = "identités";
  }
}
