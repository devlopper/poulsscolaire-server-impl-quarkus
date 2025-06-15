package org.cyk.system.poulsscolaire.server.impl.business.stockdistribution;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistribution;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link StockDistribution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<StockDistribution, StockDistributionPersistence,
        StockDistributionDynamicQuery, StockDistributionDto, StockDistributionMapper> {

  protected StockDistributionReadByIdentifierBusiness() {
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
