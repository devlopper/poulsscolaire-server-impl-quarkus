package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.segregation.HasCode;
import ci.gouv.dgbf.extension.core.segregation.HasFeeCategoryAsStringDto;
import ci.gouv.dgbf.extension.core.segregation.HasFeeCategoryIdentifierDto;
import ci.gouv.dgbf.extension.core.segregation.HasStockAsStringDto;
import ci.gouv.dgbf.extension.core.segregation.HasStockIdentifierDto;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;

/**
 * Cette classe représente la requête dynamique de {@link StockFeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockFeeCategoryDynamicQuery extends AbstractDynamicQuery<StockFeeCategory> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public StockFeeCategoryDynamicQuery() {
    super(StockFeeCategory.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(HasStockIdentifierDto.JSON_STOCK_IDENTIFIER)
        .nameFieldName(StockFeeCategory.FIELD_STOCK_IDENTIFIER)
        .fieldName(fieldName(StockFeeCategory.FIELD_STOCK, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();

    projectionBuilder().name(HasStockAsStringDto.JSON_STOCK_AS_STRING)
        .nameFieldName(StockFeeCategory.FIELD_STOCK_AS_STRING)
        .fieldName(fieldName(StockFeeCategory.FIELD_STOCK, AbstractIdentifiableCodable.FIELD_CODE))
        .build();

    projectionBuilder().name(HasFeeCategoryIdentifierDto.JSON_FEE_CATEGORY_IDENTIFIER)
        .nameFieldName(StockFeeCategory.FIELD_FEE_CATEGORY_IDENTIFIER)
        .fieldName(
            fieldName(StockFeeCategory.FIELD_FEE_CATEGORY, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();

    projectionBuilder().name(HasFeeCategoryAsStringDto.JSON_FEE_CATEGORY_AS_STRING)
        .nameFieldName(StockFeeCategory.FIELD_FEE_CATEGORY_AS_STRING)
        .fieldName(
            fieldName(StockFeeCategory.FIELD_FEE_CATEGORY, AbstractIdentifiableCodable.FIELD_CODE))
        .build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(fieldName(StockFeeCategory.FIELD_STOCK, HasCode.FIELD_CODE)).build();
  }
}
