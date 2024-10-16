package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Branch}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchPersistence extends AbstractIdentifiablePersistence<Branch> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public BranchPersistence() {
    super(Branch.class);
    name = BranchDto.NAME;
    pluralName = BranchDto.PLURAL_NAME;
  }
}
