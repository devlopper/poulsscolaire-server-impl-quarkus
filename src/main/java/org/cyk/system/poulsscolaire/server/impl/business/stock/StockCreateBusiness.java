package org.cyk.system.poulsscolaire.server.impl.business.stock;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockService.StockCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.feecategory.FeeCategoryValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.Stock;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockPersistence;

/**
 * Cette classe représente la création d'un {@link Stock}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockCreateBusiness extends AbstractIdentifiableCreateBusiness<Stock, StockPersistence,
    StockValidator, StockCreateRequestDto> {

  @Inject
  @Getter
  StockPersistence persistence;

  @Inject
  @Getter
  StockValidator validator;

  @Inject
  FeeCategoryValidator feeCategoryValidator;

  @Override
  protected Object[] validate(StockCreateRequestDto request, StringList messages) {
    FeeCategory feeCategory = feeCategoryValidator
        .validateInstanceByIdentifier(request.getFeeCategoryIdentifier(), messages);
    return new Object[] {feeCategory};
  }

  @Override
  protected void setFields(Stock stock, Object[] array, StockCreateRequestDto request) {
    super.setFields(stock, array, request);
    stock.feeCategory = (FeeCategory) array[0];
    stock.code =
        computeCode(stock.feeCategory.code, persistence.countByFeeCategory(stock.feeCategory));
    stock.name = computeName(stock.name, stock.feeCategory.name,
        persistence.countByFeeCategory(stock.feeCategory));
  }

  String computeCode(String feeCategoryCode, long count) {
    return "Stock_%s_%s".formatted(feeCategoryCode, count);
  }

  String computeName(String value, String feeCategoryName, long count) {
    if (Core.isStringBlank(value)) {
      value = "Stock %s %s".formatted(feeCategoryName, count);
    }
    return value;
  }
}
