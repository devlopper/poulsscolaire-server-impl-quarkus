package org.cyk.system.poulsscolaire.server.impl.business.stockfeecategory;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockFeeCategoryDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockFeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockFeeCategoryDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockFeeCategoryPersistence;

/**
 * Cette classe représente l'obtention de {@link StockFeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockFeeCategoryReadOneBusiness extends
    AbstractIdentifiableReadOneBusiness<StockFeeCategory,
        StockFeeCategoryPersistence, StockFeeCategoryDynamicQuery,
        StockFeeCategoryDto, StockFeeCategoryMapper> {

  @Inject
  @Getter
  StockFeeCategoryPersistence persistence;

  @Inject
  @Getter
  StockFeeCategoryDynamicQuery dynamicQuery;

  @Inject
  @Getter
  StockFeeCategoryMapper mapper;

  /**
   * Cette méthode permet de construire.
   */
  protected StockFeeCategoryReadOneBusiness() {
    super(StockFeeCategoryDto.class);
  }
}
