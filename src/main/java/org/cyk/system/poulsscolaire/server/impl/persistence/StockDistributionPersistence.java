package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.StockDistributionDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link StockDistribution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionPersistence
    extends AbstractIdentifiableCodablePersistence<StockDistribution> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public StockDistributionPersistence() {
    super(StockDistribution.class);
    name = StockDistributionDto.NAME;
    pluralName = StockDistributionDto.PLURAL_NAME;
  }
}
