package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodableNamablePersistence;
import ci.gouv.dgbf.extension.server.persistence.query.SingleResultGetter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Stock}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockPersistence extends AbstractIdentifiableCodableNamablePersistence<Stock> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public StockPersistence() {
    super(Stock.class);
    name = StockDto.NAME;
    pluralName = StockDto.PLURAL_NAME;
  }

  /**
   * Cette méthode permet d'obtenir le compte par {@link FeeCategory}.
   *
   * @param feeCategory {@link FeeCategory}
   * @return compte par {@link FeeCategory}
   */
  public long countByFeeCategory(FeeCategory feeCategory) {
    return new SingleResultGetter<>(
        entityManager.createNamedQuery(Stock.QUERY_COUNT_BY_FEE_CATEGORY_IDENTIFIER, Long.class)
            .setParameter(Stock.FIELD_FEE_CATEGORY, feeCategory)).get();
  }
}
