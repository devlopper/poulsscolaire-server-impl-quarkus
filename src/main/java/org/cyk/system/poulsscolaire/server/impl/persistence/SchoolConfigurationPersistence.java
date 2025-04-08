package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import ci.gouv.dgbf.extension.server.persistence.query.SingleResultGetter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link SchoolConfiguration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolConfigurationPersistence
    extends AbstractIdentifiablePersistence<SchoolConfiguration> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public SchoolConfigurationPersistence() {
    super(SchoolConfiguration.class);
    name = SchoolConfigurationDto.NAME;
    pluralName = SchoolConfigurationDto.PLURAL_NAME;
  }

  /**
   * Cette méthode permet d'obtenir par identifiant {@link School}.
   *
   * @param schoolIdentifier identifiant {@link School}
   * @return {@link SchoolConfiguration}
   */
  public SchoolConfiguration getBySchoolIdentifier(String schoolIdentifier) {
    return new SingleResultGetter<>(entityManager
        .createNamedQuery(SchoolConfiguration.QUERY_READ_BY_SCHOOL_IDENTIFIER_IDENTIFIER,
            SchoolConfiguration.class)
        .setParameter(SchoolConfiguration.FIELD_SCHOOL_IDENTIFIER, schoolIdentifier)).get();
  }
}
