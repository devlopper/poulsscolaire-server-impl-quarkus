package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableNamableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import ci.gouv.dgbf.extension.server.service.api.request.ProjectionDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Set;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolFilter;

/**
 * Cette classe représente la requête dynamique de {@link School}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolDynamicQuery extends AbstractDynamicQuery<School> {

  @Inject
  @Getter
  EntityManager entityManager;

  String schoolingVariableName;
  String registrationVariableName;
  String schoolUserVariableName;

  String adjustedFeeAmountsVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public SchoolDynamicQuery() {
    super(School.class);
    schoolingVariableName = "s";
    registrationVariableName = "r";
    schoolUserVariableName = "su";

    adjustedFeeAmountsVariableName = "afa";
  }

  @PostConstruct
  void postConstruct() {
    buildProjections();

    buildJoins();

    buildPredicates();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();
  }

  void buildProjections() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    buildAmountsProjections();
  }

  void buildAmountsProjections() {
    projectionBuilder().name(SchoolDto.JSON_TOTAL_AMOUNT_AS_STRING)
        .expression(formatSum(
            fieldName(adjustedFeeAmountsVariableName, AdjustedFeeAmounts.FIELD_AMOUNT_TO_PAY)))
        .resultConsumer((i, a) -> i.totalAmountAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(SchoolDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING)
        .expression(formatSum(fieldName(adjustedFeeAmountsVariableName,
            AdjustedFeeAmounts.FIELD_REGISTRATION_AMOUNT_TO_PAY)))
        .resultConsumer((i, a) -> i.totalRegistrationAmountAsString = a.getNextAsLongFormatted())
        .build();

    projectionBuilder().name(SchoolDto.JSON_PAID_AMOUNT_AS_STRING)
        .expression(formatSum(
            fieldName(adjustedFeeAmountsVariableName, AdjustedFeeAmounts.FIELD_AMOUNT_PAID)))
        .resultConsumer((i, a) -> i.paidAmountAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(SchoolDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING)
        .expression(formatSum(fieldName(adjustedFeeAmountsVariableName,
            AdjustedFeeAmounts.FIELD_REGISTRATION_AMOUNT_PAID)))
        .resultConsumer((i, a) -> i.paidRegistrationAmountAsString = a.getNextAsLongFormatted())
        .build();

    projectionBuilder().name(SchoolDto.JSON_PAYABLE_AMOUNT_AS_STRING)
        .expression(formatSum(
            fieldName(adjustedFeeAmountsVariableName, AdjustedFeeAmounts.FIELD_AMOUNT_LEFT_TO_PAY)))
        .resultConsumer((i, a) -> i.payableAmountAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(SchoolDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING)
        .expression(formatSum(fieldName(adjustedFeeAmountsVariableName,
            AdjustedFeeAmounts.FIELD_REGISTRATION_AMOUNT_LEFT_TO_PAY)))
        .resultConsumer((i, a) -> i.payableRegistrationAmountAsString = a.getNextAsLongFormatted())
        .build();
  }

  void buildJoins() {
    joinBuilder()
        .projectionsNames(SchoolDto.JSON_TOTAL_AMOUNT_AS_STRING,
            SchoolDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING,
            SchoolDto.JSON_PAID_AMOUNT_AS_STRING, SchoolDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING,
            SchoolDto.JSON_PAYABLE_AMOUNT_AS_STRING,
            SchoolDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING)
        .with(AdjustedFeeAmounts.class).tupleVariableName(adjustedFeeAmountsVariableName)
        .fieldName(AdjustedFeeAmounts.FIELD_SCHOOL_IDENTIFIER)
        .parentFieldName(AbstractIdentifiable.FIELD_IDENTIFIER).leftInnerOrRight(true).build();

    // Jointures
    joinBuilder().predicatesNames(SchoolFilter.JSON_USER_IDENTIFIER).with(SchoolUser.class)
        .tupleVariableName(schoolUserVariableName).fieldName(SchoolUser.FIELD_SCHOOL_IDENTIFIER)
        .parentFieldName(AbstractIdentifiable.FIELD_IDENTIFIER).leftInnerOrRight(true).build();
  }

  void buildPredicates() {
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(SchoolFilter.JSON_USER_IDENTIFIER)
        .tupleVariableName(schoolUserVariableName).fieldName(SchoolUser.FIELD_USER_IDENTIFIER)
        .valueFunction(SchoolFilter::getUserIdentifier).build();
  }

  @Override
  protected boolean isGrouped(DynamicQueryParameters<School> parameters) {
    return ProjectionDto.hasOneOfNames(parameters.getProjection(),
        SchoolDto.JSON_TOTAL_AMOUNT_AS_STRING, SchoolDto.JSON_PAID_AMOUNT_AS_STRING,
        SchoolDto.JSON_PAYABLE_AMOUNT_AS_STRING, SchoolDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING,
        SchoolDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING,
        SchoolDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING);
  }

  @Override
  protected void buildGroups(DynamicQueryParameters<School> parameters, Set<String> groups) {
    /*
     * On ajoute l'identifiant pour l'apsect technique de regroupement
     */
    groups.add(fieldName(variableName, AbstractIdentifiable.FIELD_IDENTIFIER));
    /*
     * On ajoute le nom pour permettre le tri par nom
     */
    groups.add(fieldName(variableName, AbstractIdentifiableNamable.FIELD_NAME));
    /*
     * On ajoute les autres au besoin
     */
  }
}
