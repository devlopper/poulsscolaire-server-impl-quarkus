package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.segregation.HasDate;
import ci.gouv.dgbf.extension.core.segregation.HasDateAsString;
import ci.gouv.dgbf.extension.core.segregation.HasDateAsStringDto;
import ci.gouv.dgbf.extension.core.segregation.HasQuantityAsString;
import ci.gouv.dgbf.extension.core.segregation.HasQuantityAsStringDto;
import ci.gouv.dgbf.extension.core.segregation.HasStockAsString;
import ci.gouv.dgbf.extension.core.segregation.HasStockAsStringDto;
import ci.gouv.dgbf.extension.core.segregation.HasStockIdentifier;
import ci.gouv.dgbf.extension.core.segregation.HasStockIdentifierDto;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableValueBasedQuery;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.HasBranchInstanceAsString;
import org.cyk.system.poulsscolaire.server.api.configuration.HasBranchInstanceAsStringDto;
import org.cyk.system.poulsscolaire.server.api.configuration.HasBranchInstanceIdentifier;
import org.cyk.system.poulsscolaire.server.api.configuration.HasBranchInstanceIdentifierDto;

/**
 * Cette classe représente la requête dynamique de {@link StockDistribution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionDynamicQuery extends AbstractDynamicQuery<StockDistribution> {

  @Inject
  @Getter
  EntityManager entityManager;

  String branchInstanceVariableName;
  String quantityVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public StockDistributionDynamicQuery() {
    super(StockDistribution.class);
    branchInstanceVariableName = "branchInstance";
    quantityVariableName = "quantity";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(HasStockIdentifierDto.JSON_STOCK_IDENTIFIER)
        .nameFieldName(HasStockIdentifier.FIELD_STOCK_IDENTIFIER)
        .fieldName(
            fieldName(StockDistribution.FIELD_STOCK, AbstractIdentifiableDto.JSON_IDENTIFIER))
        .build();

    projectionBuilder().name(HasStockAsStringDto.JSON_STOCK_AS_STRING)
        .nameFieldName(HasStockAsString.FIELD_STOCK_AS_STRING)
        .fieldName(
            fieldName(StockDistribution.FIELD_STOCK, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(HasBranchInstanceIdentifierDto.JSON_BRANCH_INSTANCE_IDENTIFIER)
        .fieldName(HasBranchInstanceIdentifier.FIELD_BRANCH_INSTANCE_IDENTIFIER).build();

    projectionBuilder().name(HasBranchInstanceAsStringDto.JSON_BRANCH_INSTANCE_AS_STRING)
        .nameFieldName(HasBranchInstanceAsString.FIELD_BRANCH_INSTANCE_AS_STRING)
        .tupleVariableName(branchInstanceVariableName)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    projectionBuilder().name(HasQuantityAsStringDto.JSON_QUANTITY_AS_STRING)
        .nameFieldName(HasQuantityAsString.FIELD_QUANTITY_AS_STRING)
        .tupleVariableName(quantityVariableName)
        .fieldName(AbstractIdentifiableValueBasedQuery.FIELD_VALUE).nullValueIsZeroNumberString()
        .build();

    projectionBuilder().name(HasDateAsStringDto.JSON_DATE_AS_STRING)
        .nameFieldName(HasDateAsString.FIELD_DATE_AS_STRING).fieldName(HasDate.FIELD_DATE).build();

    // Jointures
    joinBuilder().projectionsNames(HasBranchInstanceAsStringDto.JSON_BRANCH_INSTANCE_AS_STRING)
        .leftInnerOrRight(true).entityClass(BranchInstance.class)
        .tupleVariableName(branchInstanceVariableName)
        .parentFieldName(HasBranchInstanceIdentifier.FIELD_BRANCH_INSTANCE_IDENTIFIER).build();

    joinBuilder().projectionsNames(HasQuantityAsStringDto.JSON_QUANTITY_AS_STRING)
        .leftInnerOrRight(true).entityClass(StockDistributionQuantity.class)
        .tupleVariableName(quantityVariableName).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();



    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }
}
