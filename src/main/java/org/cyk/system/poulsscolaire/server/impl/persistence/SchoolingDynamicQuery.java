package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import ci.gouv.dgbf.extension.server.service.api.request.ProjectionDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Set;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingFilter;

/**
 * Cette classe représente la requête dynamique de {@link Schooling}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolingDynamicQuery extends AbstractDynamicQuery<Schooling> {

  @Inject
  @Getter
  EntityManager entityManager;

  String schoolVariableName;
  String branchVariableName;
  String periodVariableName;
  String feeVariableName;
  String amountVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public SchoolingDynamicQuery() {
    super(Schooling.class);
    schoolVariableName = "s";
    branchVariableName = "b";
    periodVariableName = "p";
    feeVariableName = "f";
    amountVariableName = "a";
  }

  @PostConstruct
  void postConstruct() {
    // Projections
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(SchoolingDto.JSON_SCHOOL_IDENTIFIER)
        .fieldName(Schooling.FIELD_SCHOOL_IDENTIFIER).build();
    projectionBuilder().name(SchoolingDto.JSON_SCHOOL_AS_STRING)
        .nameFieldName(Schooling.FIELD_SCHOOL_AS_STRING).tupleVariableName(schoolVariableName)
        .fieldName(AbstractIdentifiableNamable.FIELD_NAME).build();

    projectionBuilder().name(SchoolingDto.JSON_BRANCH_IDENTIFIER)
        .fieldName(Schooling.FIELD_BRANCH_IDENTIFIER).build();
    projectionBuilder().name(SchoolingDto.JSON_BRANCH_AS_STRING)
        .nameFieldName(Schooling.FIELD_BRANCH_AS_STRING).tupleVariableName(branchVariableName)
        .fieldName(AbstractIdentifiableNamable.FIELD_NAME).build();

    projectionBuilder().name(SchoolingDto.JSON_PERIOD_IDENTIFIER)
        .fieldName(Schooling.FIELD_PERIOD_IDENTIFIER).build();
    projectionBuilder().name(SchoolingDto.JSON_PERIOD_AS_STRING)
        .nameFieldName(Schooling.FIELD_PERIOD_AS_STRING).tupleVariableName(periodVariableName)
        .fieldName(AbstractIdentifiableNamable.FIELD_NAME).build();

    projectionBuilder().name(SchoolingDto.JSON_PRE_REGISTRATION_AMOUNT)
        .fieldName(Schooling.FIELD_PRE_REGISTRATION_AMOUNT).build();
    projectionBuilder().name(SchoolingDto.JSON_PRE_REGISTRATION_AMOUNT_AS_STRING)
        .nameFieldName(Schooling.FIELD_PRE_REGISTRATION_AMOUNT_AS_STRING)
        .fieldName(Schooling.FIELD_PRE_REGISTRATION_AMOUNT).build();

    projectionBuilder().name(SchoolingDto.JSON_FEE_AMOUNT_VALUE_AS_STRING)
        .tupleVariableName(amountVariableName)
        .expression(
            String.format("COALESCE(SUM(%s.%s), 0)", amountVariableName, Amount.FIELD_VALUE))
        .resultConsumer((i, a) -> i.feeAmountValueAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(SchoolingDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_VALUE_AS_STRING)
        .tupleVariableName(amountVariableName)
        .expression(String.format("COALESCE(SUM(CASE WHEN %1$s.%2$s THEN 0 ELSE %1$s.%3$s END), 0)",
            amountVariableName, Amount.FIELD_OPTIONAL, Amount.FIELD_VALUE))
        .resultConsumer((i, a) -> i.notOptionalFeeAmountValueAsString = a.getNextAsLongFormatted())
        .build();

    projectionBuilder().name(SchoolingDto.JSON_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING)
        .tupleVariableName(amountVariableName)
        .expression(String.format("COALESCE(SUM(%s.%s), 0)", amountVariableName,
            Amount.FIELD_REGISTRATION_VALUE_PART))
        .resultConsumer(
            (i, a) -> i.feeAmountRegistrationValuePartAsString = a.getNextAsLongFormatted())
        .build();

    projectionBuilder()
        .name(SchoolingDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING)
        .tupleVariableName(amountVariableName)
        .expression(String.format("COALESCE(SUM(CASE WHEN %1$s.%2$s THEN 0 ELSE %1$s.%3$s END), 0)",
            amountVariableName, Amount.FIELD_OPTIONAL, Amount.FIELD_REGISTRATION_VALUE_PART))
        .resultConsumer((i, a) -> i.notOptionalFeeAmountRegistrationValuePartAsString =
            a.getNextAsLongFormatted())
        .build();

    // Jointures
    joinBuilder()
        .projectionsNames(SchoolingDto.JSON_FEE_AMOUNT_VALUE_AS_STRING,
            SchoolingDto.JSON_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING,
            SchoolingDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_VALUE_AS_STRING,
            SchoolingDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING)
        .with(Fee.class).tupleVariableName(feeVariableName).fieldName(Fee.FIELD_SCHOOLING)
        .parentFieldName(null).leftInnerOrRight(true).build();

    joinBuilder()
        .projectionsNames(SchoolingDto.JSON_FEE_AMOUNT_VALUE_AS_STRING,
            SchoolingDto.JSON_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING,
            SchoolingDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_VALUE_AS_STRING,
            SchoolingDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING)
        .with(Amount.class).tupleVariableName(amountVariableName)
        .parentTupleVariableName(feeVariableName)
        .parentFieldName(AbstractAmountContainer.FIELD_AMOUNT).leftInnerOrRight(true).build();

    joinBuilder().projectionsNames(SchoolingDto.JSON_SCHOOL_AS_STRING)
        .predicatesNames(SchoolingDto.JSON_SCHOOL_IDENTIFIER).entityName(School.ENTITY_NAME)
        .tupleVariableName(schoolVariableName).parentFieldName(Schooling.FIELD_SCHOOL_IDENTIFIER)
        .leftInnerOrRight(true).build();

    joinBuilder().projectionsNames(SchoolingDto.JSON_BRANCH_AS_STRING)
        .entityName(Branch.ENTITY_NAME).tupleVariableName(branchVariableName)
        .parentFieldName(Schooling.FIELD_BRANCH_IDENTIFIER).leftInnerOrRight(true).build();

    joinBuilder().projectionsNames(SchoolingDto.JSON_PERIOD_AS_STRING)
        .predicatesNames(SchoolingDto.JSON_PERIOD_IDENTIFIER).entityName(Period.ENTITY_NAME)
        .tupleVariableName(periodVariableName).parentFieldName(Schooling.FIELD_PERIOD_IDENTIFIER)
        .leftInnerOrRight(true).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(SchoolingFilter.JSON_SCHOOL_IDENTIFIER)
        .tupleVariableName(schoolVariableName).fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(SchoolingFilter::getSchoolIdentifier).build();

    predicateBuilder().name(SchoolingFilter.JSON_PERIOD_IDENTIFIER)
        .tupleVariableName(periodVariableName).fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(SchoolingFilter::getPeriodIdentifier).build();

    predicateBuilder().name(SchoolingFilter.JSON_FEE_AMOUNT_OPTIONAL)
        .tupleVariableName(feeVariableName)
        .fieldName(fieldName(AbstractAmountContainer.FIELD_AMOUNT, Amount.FIELD_OPTIONAL))
        .valueFunction(SchoolingFilter::getFeeAmountOptional).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }

  @Override
  protected boolean isGrouped(DynamicQueryParameters<Schooling> parameters) {
    return ProjectionDto.hasOneOfNames(parameters.getProjection(),
        SchoolingDto.JSON_SCHOOL_IDENTIFIER, SchoolingDto.JSON_SCHOOL_AS_STRING,
        SchoolingDto.JSON_BRANCH_IDENTIFIER, SchoolingDto.JSON_BRANCH_AS_STRING,
        SchoolingDto.JSON_PERIOD_IDENTIFIER, SchoolingDto.JSON_PERIOD_AS_STRING,
        SchoolingDto.JSON_FEE_AMOUNT_VALUE_AS_STRING,
        SchoolingDto.JSON_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING,
        SchoolingDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_VALUE_AS_STRING,
        SchoolingDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING);
  }

  @Override
  protected void buildGroups(DynamicQueryParameters<Schooling> parameters, Set<String> groups) {
    /*
     * On ajoute l'identifiant pour l'apsect technique de regroupement
     */
    groups.add(fieldName(variableName, AbstractIdentifiable.FIELD_IDENTIFIER));
    /*
     * On ajoute le code pour permettre le tri par code
     */
    groups.add(fieldName(variableName, AbstractIdentifiableCodable.FIELD_CODE));
    /*
     * On ajoute les autres au besoin
     */
    Core.runIfTrue(ProjectionDto.hasOneOfNames(parameters.getProjection(),
        SchoolingDto.JSON_SCHOOL_IDENTIFIER), () -> {
          groups.add(fieldName(schoolVariableName, Schooling.FIELD_SCHOOL_IDENTIFIER));
        });
    Core.runIfTrue(ProjectionDto.hasOneOfNames(parameters.getProjection(),
        SchoolingDto.JSON_SCHOOL_AS_STRING), () -> {
          groups.add(fieldName(schoolVariableName, AbstractIdentifiableCodableNamable.FIELD_NAME));
        });
    Core.runIfTrue(ProjectionDto.hasOneOfNames(parameters.getProjection(),
        SchoolingDto.JSON_BRANCH_IDENTIFIER), () -> {
          groups.add(fieldName(branchVariableName, Schooling.FIELD_BRANCH_IDENTIFIER));
        });
    Core.runIfTrue(ProjectionDto.hasOneOfNames(parameters.getProjection(),
        SchoolingDto.JSON_BRANCH_AS_STRING), () -> {
          groups.add(fieldName(branchVariableName, AbstractIdentifiableCodableNamable.FIELD_NAME));
        });
    Core.runIfTrue(ProjectionDto.hasOneOfNames(parameters.getProjection(),
        SchoolingDto.JSON_PERIOD_IDENTIFIER), () -> {
          groups.add(fieldName(periodVariableName, Schooling.FIELD_PERIOD_IDENTIFIER));
        });
    Core.runIfTrue(ProjectionDto.hasOneOfNames(parameters.getProjection(),
        SchoolingDto.JSON_PERIOD_AS_STRING), () -> {
          groups.add(fieldName(periodVariableName, AbstractIdentifiableCodableNamable.FIELD_NAME));
        });
  }
}
