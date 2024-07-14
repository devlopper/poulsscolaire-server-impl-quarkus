package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
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
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolDto;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationDto;

/**
 * Cette classe représente la requête dynamique de {@link Registration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class RegistrationDynamicQuery extends AbstractDynamicQuery<Registration> {

  @Inject
  @Getter
  EntityManager entityManager;

  String adjustedFeeAmountsVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public RegistrationDynamicQuery() {
    super(Registration.class);
    adjustedFeeAmountsVariableName = "afa";
  }

  @PostConstruct
  void postConstruct() {
    buildProjections();

    // Jointures
    buildJoins();

    // Prédicats
    buildPredicates();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }

  void buildProjections() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(RegistrationDto.JSON_STUDENT_AS_STRING)
        .nameFieldName(Registration.FIELD_STUDENT_AS_STRING)
        .fieldName(fieldName(Registration.FIELD_STUDENT, Student.FIELD_IDENTITY,
            Identity.FIELD_FIRST_NAME))
        .build();

    projectionBuilder().name(RegistrationDto.JSON_SCHOOLING_AS_STRING)
        .nameFieldName(Registration.FIELD_SCHOOLING_AS_STRING)
        .fieldName(fieldName(Registration.FIELD_SCHOOLING, AbstractIdentifiableCodable.FIELD_CODE))
        .build();

    projectionBuilder().name(RegistrationDto.JSON_ASSIGNMENT_TYPE_AS_STRING)
        .nameFieldName(Registration.FIELD_ASSIGNMENT_TYPE_AS_STRING)
        .fieldName(fieldName(Registration.FIELD_ASSIGNMENT_TYPE,
            AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(RegistrationDto.JSON_SENIORITY_AS_STRING)
        .nameFieldName(Registration.FIELD_SENIORITY_AS_STRING)
        .fieldName(
            fieldName(Registration.FIELD_SENIORITY, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

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
        .projectionsNames(RegistrationDto.JSON_TOTAL_AMOUNT_AS_STRING,
            RegistrationDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING,
            RegistrationDto.JSON_PAID_AMOUNT_AS_STRING,
            RegistrationDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING,
            RegistrationDto.JSON_PAYABLE_AMOUNT_AS_STRING,
            RegistrationDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING)
        .with(AdjustedFeeAmounts.class).tupleVariableName(adjustedFeeAmountsVariableName)
        .fieldName(AdjustedFeeAmounts.FIELD_REGISTRATION_IDENTIFIER)
        .parentFieldName(AbstractIdentifiable.FIELD_IDENTIFIER).leftInnerOrRight(true).build();
  }

  void buildPredicates() {
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();
  }

  @Override
  protected boolean isGrouped(DynamicQueryParameters<Registration> parameters) {
    return ProjectionDto.hasOneOfNames(parameters.getProjection(),
        RegistrationDto.JSON_TOTAL_AMOUNT_AS_STRING, RegistrationDto.JSON_PAID_AMOUNT_AS_STRING,
        RegistrationDto.JSON_PAYABLE_AMOUNT_AS_STRING,
        RegistrationDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING,
        RegistrationDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING,
        RegistrationDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING);
  }

  @Override
  protected void buildGroups(DynamicQueryParameters<Registration> parameters, Set<String> groups) {
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
  }
}
