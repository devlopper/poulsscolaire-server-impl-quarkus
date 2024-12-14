package org.cyk.system.poulsscolaire.server.impl.business.stock;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockService.StockUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Stock;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockPersistence;

/**
 * Cette classe représente la mise à jour de {@link Stock}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockUpdateBusiness extends AbstractIdentifiableUpdateBusiness<Stock,
    StockPersistence, StockValidator, StockUpdateRequestDto> {

  @Inject
  @Getter
  StockPersistence persistence;

  @Inject
  @Getter
  StockValidator validator;
}
