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
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionFilter;

/**
 * Cette classe représente la requête dynamique de {@link FundingExecution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingExecutionDynamicQuery extends AbstractDynamicQuery<FundingExecution> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public FundingExecutionDynamicQuery() {
    super(FundingExecution.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(FundingExecutionDto.JSON_FUNDING_IDENTIFIER)
        .fieldName(fieldName(FundingExecution.FIELD_FUNDING, AbstractIdentifiable.FIELD_IDENTIFIER))
        .nameFieldName(FundingExecution.FIELD_FUNDING_IDENTIFIER).build();

    projectionBuilder().name(FundingExecutionDto.JSON_FUNDING_AS_STRING)
        .fieldName(fieldName(FundingExecution.FIELD_FUNDING, Funding.FIELD_BUDGET,
            AbstractIdentifiableCodableNamable.FIELD_NAME))
        .nameFieldName(FundingExecution.FIELD_FUNDING_AS_STRING).build();

    projectionBuilder().name(FundingExecutionDto.JSON_AMOUNT)
        .fieldName(FundingExecution.FIELD_AMOUNT).build();

    projectionBuilder().name(FundingExecutionDto.JSON_AMOUNT_AS_STRING)
        .fieldName(FundingExecution.FIELD_AMOUNT)
        .nameFieldName(FundingExecution.FIELD_AMOUNT_AS_STRING).build();

    // Jointures

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(FundingExecutionFilter.JSON_FUNDING_IDENTIFIER)
        .fieldName(fieldName(FundingExecution.FIELD_FUNDING, AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(FundingExecutionFilter::getFundingIdentifier).build();

    // Ordres par défaut
    orderBuilder()
        .fieldName(
            fieldName(FundingExecution.FIELD_FUNDING, Funding.FIELD_BUDGET, Budget.FIELD_YEAR))
        .ascending(false).build();
    orderBuilder().fieldName(fieldName(FundingExecution.FIELD_FUNDING, Funding.FIELD_MONTH))
        .ascending(false).build();
  }
}
