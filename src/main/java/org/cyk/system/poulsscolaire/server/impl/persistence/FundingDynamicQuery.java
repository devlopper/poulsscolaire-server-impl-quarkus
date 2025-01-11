package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.SingleResultGetter;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import ci.gouv.dgbf.extension.server.service.api.request.FilterDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetStatus;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingFilter;

/**
 * Cette classe représente la requête dynamique de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingDynamicQuery extends AbstractDynamicQuery<Funding> {

  @Inject
  @Getter
  EntityManager entityManager;

  String departmentVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public FundingDynamicQuery() {
    super(Funding.class);
    departmentVariableName = "d";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(FundingDto.JSON_BUDGET_IDENTIFIER)
        .fieldName(fieldName(Funding.FIELD_BUDGET, AbstractIdentifiable.FIELD_IDENTIFIER))
        .nameFieldName(Funding.FIELD_BUDGET_IDENTIFIER).build();

    projectionBuilder().name(FundingDto.JSON_BUDGET_AS_STRING)
        .fieldName(fieldName(Funding.FIELD_BUDGET, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .nameFieldName(Funding.FIELD_BUDGET_AS_STRING).build();

    projectionBuilder().name(FundingDto.JSON_MONTH).fieldName(Funding.FIELD_MONTH).build();

    projectionBuilder().name(FundingDto.JSON_MONTH_AS_STRING).fieldName(Funding.FIELD_MONTH)
        .resultConsumer((i, a) -> i.monthAsString =
            a.getNextAs(Month.class).getDisplayName(TextStyle.FULL, Locale.FRENCH))
        .build();

    projectionBuilder().name(FundingDto.JSON_DEPARTMENT_IDENTIFIER)
        .fieldName(Funding.FIELD_DEPARTMENT_IDENTIFIER).build();

    projectionBuilder().name(FundingDto.JSON_DEPARTMENT_AS_STRING)
        .tupleVariableName(departmentVariableName)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME)
        .nameFieldName(Funding.FIELD_DEPARTMENT_AS_STRING).build();

    projectionBuilder().name(FundingDto.JSON_ACCOUNTING_ACCOUNT_IDENTIFIER)
        .fieldName(
            fieldName(Funding.FIELD_ACCOUNTING_ACCOUNT, AbstractIdentifiable.FIELD_IDENTIFIER))
        .nameFieldName(Funding.FIELD_ACCOUNTING_ACCOUNT_IDENTIFIER).build();

    projectionBuilder().name(FundingDto.JSON_ACCOUNTING_ACCOUNT_AS_STRING)
        .expression(formatConcatCodeName(fieldName(variableName, Funding.FIELD_ACCOUNTING_ACCOUNT)))
        .resultConsumer((i, a) -> i.accountingAccountAsString = a.getNextAsString()).build();

    projectionBuilder().name(FundingDto.JSON_SOURCE_IDENTIFIER)
        .fieldName(fieldName(Funding.FIELD_SOURCE, AbstractIdentifiable.FIELD_IDENTIFIER))
        .nameFieldName(Funding.FIELD_SOURCE_IDENTIFIER).build();

    projectionBuilder().name(FundingDto.JSON_SOURCE_AS_STRING)
        .expression(formatConcatName(fieldName(variableName, Funding.FIELD_SOURCE)))
        .resultConsumer((i, a) -> i.sourceAsString = a.getNextAsString()).build();

    projectionBuilder().name(FundingDto.JSON_AMOUNT).fieldName(Funding.FIELD_AMOUNT).build();

    projectionBuilder().name(FundingDto.JSON_AMOUNT_AS_STRING).fieldName(Funding.FIELD_AMOUNT)
        .nameFieldName(Funding.FIELD_AMOUNT_AS_STRING).build();

    projectionBuilder().name(FundingDto.JSON_AMOUNT_INPUTABLE)
        .fieldName(fieldName(Funding.FIELD_BUDGET, Budget.FIELD_STATUS))
        .resultConsumer((i, a) -> i.amountInputable =
            Core.isNotTrue(BudgetStatus.APPROVED.equals(a.getNext(BudgetStatus.class))))
        .build();

    projectionBuilder().name(FundingDto.JSON_JUSTIFICATION).fieldName(Funding.FIELD_JUSTIFICATION)
        .build();

    // Jointures
    joinBuilder().projectionsNames(FundingDto.JSON_DEPARTMENT_AS_STRING)
        .predicatesNames(FundingFilter.JSON_DEPARTMENT_IDENTIFIER).leftInnerOrRight(true)
        .entityClass(Department.class).tupleVariableName(departmentVariableName)
        .parentFieldName(Funding.FIELD_DEPARTMENT_IDENTIFIER).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(FundingFilter.JSON_BUDGET_IDENTIFIER)
        .fieldName(fieldName(Funding.FIELD_BUDGET, AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(FundingFilter::getBudgetIdentifier).build();

    predicateBuilder().name(FundingFilter.JSON_DEPARTMENT_IDENTIFIER)
        .fieldName(Funding.FIELD_DEPARTMENT_IDENTIFIER)
        .valueFunction(FundingFilter::getDepartmentIdentifier).build();

    predicateBuilder().name(FundingFilter.JSON_MONTH).fieldName(Funding.FIELD_MONTH)
        .valueFunction(FundingFilter::getMonth).build();

    // Ordres par défaut
    orderBuilder().fieldName(
        fieldName(Funding.FIELD_ACCOUNTING_ACCOUNT, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();
  }

  /**
   * Cette méthode permet de sommer le montant.
   *
   * @param filter filtre
   * @return somme du montant
   */
  public Long sumAmount(FilterDto filter) {
    DynamicQueryParameters<Funding> dynamicQueryParameters = new DynamicQueryParameters<>();
    dynamicQueryParameters.setProjectionsStringSupplier(() -> "SUM(t.amount)");
    dynamicQueryParameters.setFilter(filter);
    TypedQuery<Object[]> query = buildQuery(dynamicQueryParameters);
    return (Long) new SingleResultGetter<Object[]>(query).get()[0];
  }
}
