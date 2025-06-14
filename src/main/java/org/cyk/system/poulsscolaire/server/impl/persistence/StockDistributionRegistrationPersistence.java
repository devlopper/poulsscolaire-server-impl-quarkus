package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.StockDistributionRegistrationDto;

/**
 * Cette classe représente les fonctionnalités de persistance de
 * {@link StockDistributionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionRegistrationPersistence
    extends AbstractIdentifiablePersistence<StockDistributionRegistration> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public StockDistributionRegistrationPersistence() {
    super(StockDistributionRegistration.class);
    name = StockDistributionRegistrationDto.NAME;
    pluralName = StockDistributionRegistrationDto.PLURAL_NAME;
  }
}
