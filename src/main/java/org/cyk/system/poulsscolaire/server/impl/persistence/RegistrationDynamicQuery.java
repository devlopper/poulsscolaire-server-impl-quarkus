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
  String adjustedFeeVariableName;
  String amountVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public RegistrationDynamicQuery() {
    super(Registration.class);
    adjustedFeeVariableName = "af";
    amountVariableName = "a";
  }

  @PostConstruct
  void postConstruct() {
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

    projectionBuilder().name(RegistrationDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_VALUE_AS_STRING)
        .tupleVariableName(amountVariableName)
        .expression(String.format("COALESCE(SUM(CASE WHEN %1$s.%2$s THEN 0 ELSE %1$s.%3$s END), 0)",
            amountVariableName, Amount.FIELD_OPTIONAL, Amount.FIELD_VALUE))
        .resultConsumer(
            (i, a) -> i.notOptionalFeeAmountValueAsString = a.getNextAsLongFormatted())
        .build();

    projectionBuilder()
        .name(RegistrationDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING)
        .tupleVariableName(amountVariableName)
        .expression(String.format("COALESCE(SUM(CASE WHEN %1$s.%2$s THEN 0 ELSE %1$s.%3$s END), 0)",
            amountVariableName, Amount.FIELD_OPTIONAL, Amount.FIELD_REGISTRATION_VALUE_PART))
        .resultConsumer((i, a) -> i.notOptionalFeeAmountRegistrationValuePartAsString =
            a.getNextAsLongFormatted())
        .build();

    // Jointures
    joinBuilder()
        .projectionsNames(RegistrationDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_VALUE_AS_STRING,
            RegistrationDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING)
        .with(AdjustedFee.class).tupleVariableName(adjustedFeeVariableName)
        .fieldName(AdjustedFee.FIELD_REGISTRATION).parentFieldName(null).leftInnerOrRight(true)
        .build();

    joinBuilder()
        .projectionsNames(RegistrationDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_VALUE_AS_STRING,
            RegistrationDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING)
        .with(Amount.class).tupleVariableName(amountVariableName)
        .parentTupleVariableName(adjustedFeeVariableName)
        .parentFieldName(AbstractAmountContainer.FIELD_AMOUNT).leftInnerOrRight(true).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }

  @Override
  protected boolean isGrouped(DynamicQueryParameters<Registration> parameters) {
    return ProjectionDto.hasOneOfNames(parameters.getProjection(),
        RegistrationDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_VALUE_AS_STRING,
        RegistrationDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING);
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
