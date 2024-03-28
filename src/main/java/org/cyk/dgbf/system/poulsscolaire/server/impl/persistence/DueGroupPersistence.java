package org.cyk.dgbf.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodableNamablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link DueGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DueGroupPersistence extends AbstractIdentifiableCodableNamablePersistence<DueGroup> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public DueGroupPersistence() {
    super(DueGroup.class);
    name = "groupe échéance";
    pluralName = "groupes échéance";
  }
}
