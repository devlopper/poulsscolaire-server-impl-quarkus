package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link BranchInstance}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchInstancePersistence extends AbstractIdentifiablePersistence<BranchInstance> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public BranchInstancePersistence() {
    super(BranchInstance.class);
    name = BranchInstanceDto.NAME;
    pluralName = BranchInstanceDto.PLURAL_NAME;
  }
}
