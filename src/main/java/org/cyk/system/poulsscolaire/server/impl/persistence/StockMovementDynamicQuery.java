package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementDto;

/**
 * Cette classe représente la requête dynamique de {@link StockMovement}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockMovementDynamicQuery extends AbstractDynamicQuery<StockMovement> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public StockMovementDynamicQuery() {
    super(StockMovement.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(StockMovementDto.JSON_STOCK_IDENTIFIER)
        .nameFieldName(StockMovement.FIELD_STOCK_IDENTIFIER)
        .fieldName(fieldName(StockMovement.FIELD_STOCK, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();


    projectionBuilder().name(StockMovementDto.JSON_STOCK_AS_STRING)
        .nameFieldName(StockMovement.FIELD_STOCK_AS_STRING)
        .fieldName(
            fieldName(StockMovement.FIELD_STOCK, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(StockMovementDto.JSON_QUANTITY).fieldName(StockMovement.FIELD_QUANTITY)
        .build();

    projectionBuilder().name(StockMovementDto.JSON_QUANTITY_AS_STRING)
        .nameFieldName(StockMovement.FIELD_QUANTITY_AS_STRING)
        .fieldName(StockMovement.FIELD_QUANTITY).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut


  }
}
