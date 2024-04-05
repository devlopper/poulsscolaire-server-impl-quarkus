package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Student}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StudentPersistence extends AbstractIdentifiableCodablePersistence<Student> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public StudentPersistence() {
    super(Student.class);
    name = "élève";
    pluralName = "élèves";
  }
}
