package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Schooling}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolingPersistence extends AbstractIdentifiablePersistence<Schooling> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public SchoolingPersistence() {
    super(Schooling.class);
    name = SchoolingDto.NAME;
    pluralName = SchoolingDto.PLURAL_NAME;
  }
}
