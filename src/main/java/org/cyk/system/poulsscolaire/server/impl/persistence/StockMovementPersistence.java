package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import ci.gouv.dgbf.extension.server.persistence.query.SingleResultGetter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link StockMovement}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockMovementPersistence extends AbstractIdentifiablePersistence<StockMovement> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public StockMovementPersistence() {
    super(StockMovement.class);
    name = StockMovementDto.NAME;
    pluralName = StockMovementDto.PLURAL_NAME;
  }

  /**
   * Cette méthode permet de sommer la quantité par {@link Stock}.
   *
   * @param stock {@link Stock}
   * @return quantité par {@link Stock}
   */
  public long sumQuantityByStock(Stock stock) {
    return new SingleResultGetter<>(entityManager
        .createNamedQuery(StockMovement.QUERY_SUM_QUANTITY_BY_STOCK_IDENTIFIER, Long.class)
        .setParameter(StockMovement.FIELD_STOCK, stock)).get();
  }
}
