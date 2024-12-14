package org.cyk.system.poulsscolaire.server.impl.business.stock;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Stock;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockPersistence;

/**
 * Cette class représente un validateur de {@link Stock}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockValidator extends AbstractIdentifiableCodableNamableValidator<Stock> {

  @Inject
  @Getter
  private StockPersistence persistence;

}
