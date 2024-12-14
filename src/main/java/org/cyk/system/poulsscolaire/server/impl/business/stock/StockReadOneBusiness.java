package org.cyk.system.poulsscolaire.server.impl.business.stock;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Stock;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockPersistence;

/**
 * Cette classe représente l'obtention de {@link Stock}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockReadOneBusiness extends AbstractIdentifiableReadOneBusiness<Stock,
    StockPersistence, StockDynamicQuery, StockDto, StockMapper> {

  protected StockReadOneBusiness() {
    super(StockDto.class);
  }

  @Inject
  @Getter
  StockPersistence persistence;

  @Inject
  @Getter
  StockDynamicQuery dynamicQuery;

  @Inject
  @Getter
  StockMapper mapper;
}
