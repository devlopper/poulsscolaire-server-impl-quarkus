package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodableNamablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Branch}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchPersistence extends AbstractIdentifiableCodableNamablePersistence<Branch> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public BranchPersistence() {
    super(Branch.class);
    name = "branche";
    pluralName = "branches";
  }
}
