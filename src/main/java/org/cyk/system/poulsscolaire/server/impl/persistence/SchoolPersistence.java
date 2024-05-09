package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link School}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolPersistence extends AbstractIdentifiablePersistence<School> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public SchoolPersistence() {
    super(School.class);
    name = "école";
    pluralName = name + "s";
  }
}
