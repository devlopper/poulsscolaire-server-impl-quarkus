package org.cyk.system.poulsscolaire.server.impl.business.stockdistribution;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionService.StockDistributionCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Stock;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistribution;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionPersistence;

/**
 * Cette classe représente la création d'un {@link StockDistribution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionCreateBusiness
    extends AbstractIdentifiableCreateBusiness<StockDistribution, StockDistributionPersistence,
        StockDistributionValidator, StockDistributionCreateRequestDto> {

  @Inject
  @Getter
  StockDistributionPersistence persistence;

  @Inject
  @Getter
  StockDistributionValidator validator;

  @Inject
  StockValidator stockValidator;

  @Override
  protected Object[] validate(StockDistributionCreateRequestDto request, StringList messages) {
    Stock stock =
        stockValidator.validateInstanceByIdentifier(request.getStockIdentifier(), messages);
    return new Object[] {stock};
  }

  @Override
  protected void setFields(StockDistribution stockDistribution, Object[] array,
      StockDistributionCreateRequestDto request) {
    super.setFields(stockDistribution, array, request);
    stockDistribution.stock = (Stock) array[0];
    stockDistribution.branchInstanceIdentifier = request.getBranchInstanceIdentifier();
    stockDistribution.date = request.getDate();
    String branchCode = stockDistribution.branchInstanceIdentifier;
    stockDistribution.code =
        computeCode(branchCode, persistence.countAll());
  }

  String computeCode(String branchCode, long count) {
    return "D_%s_%s".formatted(branchCode, count);
  }
}
