package org.cyk.system.poulsscolaire.server.impl.business.stockdistribution;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistribution;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionPersistence;

/**
 * Cette classe représente l'obtention de {@link StockDistribution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionReadOneBusiness
    extends AbstractIdentifiableReadOneBusiness<StockDistribution, StockDistributionPersistence,
        StockDistributionDynamicQuery, StockDistributionDto, StockDistributionMapper> {

  protected StockDistributionReadOneBusiness() {
    super(StockDistributionDto.class);
  }

  @Inject
  @Getter
  StockDistributionPersistence persistence;

  @Inject
  @Getter
  StockDistributionDynamicQuery dynamicQuery;

  @Inject
  @Getter
  StockDistributionMapper mapper;
}
