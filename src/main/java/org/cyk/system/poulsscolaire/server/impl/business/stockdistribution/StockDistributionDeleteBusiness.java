package org.cyk.system.poulsscolaire.server.impl.business.stockdistribution;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistribution;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionPersistence;

/**
 * Cette classe représente la suppression de {@link StockDistribution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionDeleteBusiness
    extends AbstractIdentifiableDeleteBusiness<StockDistribution, StockDistributionPersistence,
        StockDistributionValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  StockDistributionPersistence persistence;

  @Inject
  @Getter
  StockDistributionValidator validator;
}
