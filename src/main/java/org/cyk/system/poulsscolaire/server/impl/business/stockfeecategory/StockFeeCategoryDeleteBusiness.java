package org.cyk.system.poulsscolaire.server.impl.business.stockfeecategory;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockFeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockFeeCategoryPersistence;

/**
 * Cette classe représente la suppression de {@link StockFeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockFeeCategoryDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    StockFeeCategory, StockFeeCategoryPersistence, StockFeeCategoryValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  StockFeeCategoryPersistence persistence;

  @Inject
  @Getter
  StockFeeCategoryValidator validator;
}
