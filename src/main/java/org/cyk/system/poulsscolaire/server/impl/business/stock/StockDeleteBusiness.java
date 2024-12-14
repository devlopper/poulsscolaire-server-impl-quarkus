package org.cyk.system.poulsscolaire.server.impl.business.stock;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Stock;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockPersistence;

/**
 * Cette classe représente la suppression de {@link Stock}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    Stock, StockPersistence, StockValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  StockPersistence persistence;

  @Inject
  @Getter
  StockValidator validator;
}
