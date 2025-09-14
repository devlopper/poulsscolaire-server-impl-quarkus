package org.cyk.system.poulsscolaire.server.impl.business.stockfeecategory;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockFeeCategoryService.StockFeeCategoryUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryValidator;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockFeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockFeeCategoryPersistence;

/**
 * Cette classe représente la mise à jour de {@link StockFeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockFeeCategoryUpdateBusiness
    extends AbstractIdentifiableUpdateBusiness<StockFeeCategory, StockFeeCategoryPersistence,
        StockFeeCategoryValidator, StockFeeCategoryUpdateRequestDto> {

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
  protected void validate(StockFeeCategoryUpdateRequestDto request, StringList messages,
      StockFeeCategory stockFeeCategory) {
    super.validate(request, messages, stockFeeCategory);
    stockFeeCategory.setStock(
        stockValidator.validateInstanceByIdentifier(request.getStockIdentifier(), messages));
    stockFeeCategory.setFeeCategory(feeCategoryValidator
        .validateInstanceByIdentifier(request.getFeeCategoryIdentifier(), messages));
  }
}
