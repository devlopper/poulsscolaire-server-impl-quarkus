package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.DepartmentDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Department}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DepartmentPersistence extends AbstractIdentifiablePersistence<Department> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public DepartmentPersistence() {
    super(Department.class);
    name = DepartmentDto.NAME;
    pluralName = DepartmentDto.PLURAL_NAME;
  }
}
