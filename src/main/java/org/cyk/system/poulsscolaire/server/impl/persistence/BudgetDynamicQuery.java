package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableNamableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetDto;

/**
 * Cette classe représente la requête dynamique de {@link Budget}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BudgetDynamicQuery extends AbstractDynamicQuery<Budget> {

  @Inject
  @Getter
  EntityManager entityManager;

  String schoolVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public BudgetDynamicQuery() {
    super(Budget.class);
    schoolVariableName = "s";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    projectionBuilder().name(BudgetDto.JSON_SCHOOL_IDENTIFIER)
        .fieldName(Budget.FIELD_SCHOOL_IDENTIFIER).build();

    projectionBuilder().name(BudgetDto.JSON_SCHOOL_AS_STRING).tupleVariableName(schoolVariableName)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME)
        .nameFieldName(Budget.FIELD_SCHOOL_AS_STRING).build();

    projectionBuilder().name(BudgetDto.JSON_ACCOUNTING_PLAN_IDENTIFIER)
        .fieldName(fieldName(Budget.FIELD_ACCOUNTING_PLAN, AbstractIdentifiable.FIELD_IDENTIFIER))
        .nameFieldName(Budget.FIELD_ACCOUNTING_PLAN_IDENTIFIER).build();

    projectionBuilder().name(BudgetDto.JSON_ACCOUNTING_PLAN_AS_STRING)
        .fieldName(
            fieldName(Budget.FIELD_ACCOUNTING_PLAN, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .nameFieldName(Budget.FIELD_ACCOUNTING_PLAN_AS_STRING).build();

    projectionBuilder().name(BudgetDto.JSON_YEAR).fieldName(Budget.FIELD_YEAR).build();

    // Jointures
    joinBuilder().projectionsNames(BudgetDto.JSON_SCHOOL_AS_STRING).entityClass(School.class)
        .tupleVariableName(schoolVariableName).parentFieldName(Budget.FIELD_SCHOOL_IDENTIFIER)
        .leftInnerOrRight(true).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(Budget.FIELD_YEAR).build();
  }
}
