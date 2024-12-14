package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodableNamablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Seniority}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SeniorityPersistence extends AbstractIdentifiableCodableNamablePersistence<Seniority> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public SeniorityPersistence() {
    super(Seniority.class);
    name = SeniorityDto.NAME;
    pluralName = SeniorityDto.PLURAL_NAME;
  }
}
