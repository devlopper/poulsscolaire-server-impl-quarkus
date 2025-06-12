package org.cyk.system.poulsscolaire.server.impl.business.stockdistribution;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.StockDistributionService.StockDistributionUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistribution;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionPersistence;

/**
 * Cette classe représente la mise à jour de {@link StockDistribution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionUpdateBusiness
    extends AbstractIdentifiableUpdateBusiness<StockDistribution, StockDistributionPersistence,
        StockDistributionValidator, StockDistributionUpdateRequestDto> {

  @Inject
  @Getter
  StockDistributionPersistence persistence;

  @Inject
  @Getter
  StockDistributionValidator validator;

  @Inject
  StockValidator stockValidator;

  @Override
  protected void validate(StockDistributionUpdateRequestDto request, StringList messages,
      StockDistribution stockDistribution) {
    super.validate(request, messages, stockDistribution);
    stockDistribution.stock =
        stockValidator.validateInstanceByIdentifier(request.getStockIdentifier(), messages);
  }

  @Override
  protected void prepare(StockDistribution stockDistribution,
      StockDistributionUpdateRequestDto request) {
    super.prepare(stockDistribution, request);
    stockDistribution.branchInstanceIdentifier = request.getBranchInstanceIdentifier();
  }
}
