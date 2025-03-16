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
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingFilter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingStatus;

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

    projectionBuilder().name(AbstractIdentifiableDto.JSON_AS_STRING)
        .expression(formatConcat(
            fieldName(Funding.FIELD_BUDGET, AbstractIdentifiableCodableNamable.FIELD_NAME), "' '",
            Funding.FIELD_MONTH, "' '",
            fieldName(Funding.FIELD_SOURCE, AbstractIdentifiableCodableNamable.FIELD_NAME), "' '",
            Funding.FIELD_AMOUNT))
        .resultConsumer((i, a) -> i.asString = a.getNextAsString()).build();

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

    projectionBuilder().name(FundingDto.JSON_AMOUNT_INPUTABLE).fieldName(Funding.FIELD_STATUS)
        .resultConsumer((i, a) -> i.amountInputable =
            Core.isNotTrue(FundingStatus.APPROVED.equals(a.getNext(FundingStatus.class))))
        .build();

    projectionBuilder().name(FundingDto.JSON_JUSTIFICATION).fieldName(Funding.FIELD_JUSTIFICATION)
        .build();

    projectionBuilder().name(FundingDto.JSON_STATUS).fieldName(Funding.FIELD_STATUS).build();

    projectionBuilder().name(FundingDto.JSON_STATUS_AS_STRING)
        .nameFieldName(Funding.FIELD_STATUS_AS_STRING).fieldName(Funding.FIELD_STATUS)
        .resultConsumer((i, a) -> i.statusAsString = a.getNextAs(FundingStatus.class).getName())
        .build();

    projectionBuilder().name(FundingDto.JSON_STATUS_REASON).fieldName(Funding.FIELD_STATUS_REASON)
        .build();

    buildStatusableProjection(FundingDto.JSON_TRANSMITABLE, Funding.FIELD_TRANSMITABLE,
        FundingStatus.TRANSMITTED, (a, b) -> a.transmitable = b);

    buildStatusableProjection(FundingDto.JSON_ACCEPTABLE, Funding.FIELD_ACCEPTABLE,
        FundingStatus.ACCEPTED, (a, b) -> a.acceptable = b);

    buildStatusableProjection(FundingDto.JSON_APPROVABLE, Funding.FIELD_APPROVABLE,
        FundingStatus.APPROVED, (a, b) -> a.approvable = b);

    buildStatusableProjection(FundingDto.JSON_RETURNABLE, Funding.FIELD_RETURNABLE,
        FundingStatus.RETURNED, (a, b) -> a.returnable = b);
    
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
  
  void buildStatusableProjection(String name, String fieldName, FundingStatus status,
      BiConsumer<Funding, Boolean> booleanConsumer) {
    projectionBuilder().name(name).nameFieldName(fieldName)
        .expression(status.getPrevious().stream()
            .map(p -> String.format(STATUS_EQUALS_FORMAT, FundingStatus.class.getName(), p.name()))
            .collect(Collectors.joining(OR)))
        .resultConsumer((i, a) -> booleanConsumer.accept(i, a.getNextAsBoolean())).build();
  }

  static final String STATUS_EQUALS_FORMAT = "t.status = %s.%s";
  static final String OR = " OR ";

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
