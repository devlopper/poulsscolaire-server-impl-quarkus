package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.segregation.HasDeadline;
import ci.gouv.dgbf.extension.core.segregation.HasDeadlineAsStringDto;
import ci.gouv.dgbf.extension.core.segregation.HasDeadlineDto;
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
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetStatus;

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
  String amountVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public BudgetDynamicQuery() {
    super(Budget.class);
    schoolVariableName = "s";
    amountVariableName = "ba";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    projectionBuilder().name(BudgetDto.JSON_AMOUNT_AS_STRING).tupleVariableName(amountVariableName)
        .fieldName(BudgetAmount.FIELD_VALUE).nameFieldName(Budget.FIELD_AMOUNT_AS_STRING).build();

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

    projectionBuilder().name(HasDeadlineDto.JSON_DEADLINE).fieldName(HasDeadline.FIELD_DEADLINE)
        .build();
    projectionBuilder().name(HasDeadlineAsStringDto.JSON_DEADLINE_AS_STRING)
        .fieldName(HasDeadline.FIELD_DEADLINE)
        .resultConsumer((i, a) -> i.deadlineAsString = a.getNextAsLocalDateTimeFormatted()).build();

    projectionBuilder().name(BudgetDto.JSON_STATUS).fieldName(Budget.FIELD_STATUS).build();

    projectionBuilder().name(BudgetDto.JSON_STATUS_AS_STRING)
        .nameFieldName(Budget.FIELD_STATUS_AS_STRING).fieldName(Budget.FIELD_STATUS)
        .resultConsumer((i, a) -> i.statusAsString = a.getNextAs(BudgetStatus.class).getName())
        .build();

    projectionBuilder().name(BudgetDto.JSON_STATUS_REASON).fieldName(Budget.FIELD_STATUS_REASON)
        .build();

    buildStatusableProjection(BudgetDto.JSON_TRANSMITABLE, Budget.FIELD_TRANSMITABLE,
        BudgetStatus.TRANSMITTED, (a, b) -> a.transmitable = b);

    buildStatusableProjection(BudgetDto.JSON_ACCEPTABLE, Budget.FIELD_ACCEPTABLE,
        BudgetStatus.ACCEPTED, (a, b) -> a.acceptable = b);

    buildStatusableProjection(BudgetDto.JSON_APPROVABLE, Budget.FIELD_APPROVABLE,
        BudgetStatus.APPROVED, (a, b) -> a.approvable = b);

    buildStatusableProjection(BudgetDto.JSON_RETURNABLE, Budget.FIELD_RETURNABLE,
        BudgetStatus.RETURNED, (a, b) -> a.returnable = b);

    // Jointures
    joinBuilder().projectionsNames(BudgetDto.JSON_SCHOOL_AS_STRING).entityClass(School.class)
        .tupleVariableName(schoolVariableName).parentFieldName(Budget.FIELD_SCHOOL_IDENTIFIER)
        .leftInnerOrRight(true).build();

    joinBuilder().projectionsNames(BudgetDto.JSON_AMOUNT_AS_STRING).leftInnerOrRight(true)
        .entityClass(BudgetAmount.class).tupleVariableName(amountVariableName).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(Budget.FIELD_YEAR).ascending(false).build();
  }

  void buildStatusableProjection(String name, String fieldName, BudgetStatus status,
      BiConsumer<Budget, Boolean> booleanConsumer) {
    projectionBuilder().name(name).nameFieldName(fieldName)
        .expression(status.getPrevious().stream()
            .map(p -> String.format(STATUS_EQUALS_FORMAT, BudgetStatus.class.getName(), p.name()))
            .collect(Collectors.joining(OR)))
        .resultConsumer((i, a) -> booleanConsumer.accept(i, a.getNextAsBoolean())).build();
  }

  static final String STATUS_EQUALS_FORMAT = "t.status = %s.%s";
  static final String OR = " OR ";
}
