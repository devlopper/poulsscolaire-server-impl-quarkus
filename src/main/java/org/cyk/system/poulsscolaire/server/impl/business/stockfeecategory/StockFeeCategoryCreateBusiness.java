package org.cyk.system.poulsscolaire.server.impl.business.stockfeecategory;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockFeeCategoryService.StockFeeCategoryCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryValidator;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.Stock;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockFeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockFeeCategoryPersistence;

/**
 * Cette classe représente la création de {@link StockFeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockFeeCategoryCreateBusiness
    extends AbstractIdentifiableCreateBusiness<StockFeeCategory, StockFeeCategoryPersistence,
        StockFeeCategoryValidator, StockFeeCategoryCreateRequestDto> {

  @Inject
  @Getter
  StockFeeCategoryPersistence persistence;

  @Inject
  @Getter
  StockFeeCategoryValidator validator;

  @Inject
  StockValidator stockValidator;

  @Inject
  FeeCategoryValidator feeCategoryValidator;

  @Override
  protected Object[] validate(StockFeeCategoryCreateRequestDto request, StringList messages) {
    Stock stock =
        stockValidator.validateInstanceByIdentifier(request.getStockIdentifier(), messages);
    FeeCategory feeCategory = feeCategoryValidator
        .validateInstanceByIdentifier(request.getFeeCategoryIdentifier(), messages);
    return new Object[] {stock, feeCategory};
  }

  @Override
  protected void setFields(StockFeeCategory stockFeeCategory, Object[] array,
      StockFeeCategoryCreateRequestDto request) {
    super.setFields(stockFeeCategory, array, request);
    stockFeeCategory.setStock((Stock) array[0]);
    stockFeeCategory.setFeeCategory((FeeCategory) array[1]);
  }
}
