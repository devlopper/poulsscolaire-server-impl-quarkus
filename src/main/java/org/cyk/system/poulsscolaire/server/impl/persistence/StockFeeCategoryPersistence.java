package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockFeeCategoryDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link StockFeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockFeeCategoryPersistence extends AbstractIdentifiablePersistence<StockFeeCategory> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public StockFeeCategoryPersistence() {
    super(StockFeeCategory.class);
    name = StockFeeCategoryDto.NAME;
    pluralName = StockFeeCategoryDto.PLURAL_NAME;
  }
}
