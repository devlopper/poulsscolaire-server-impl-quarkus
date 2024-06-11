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

  @Inject
  AmountDynamicQuery amountDynamicQuery;

  String adjustedFeeVariableName;
  String amountVariableName;
  String paymentVariableName;
  String paymentAdjustedFeeVariableName;

  String querySumPayment;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public RegistrationDynamicQuery() {
    super(Registration.class);
    adjustedFeeVariableName = "af";
    amountVariableName = "a";
    paymentVariableName = "p";
    paymentAdjustedFeeVariableName = "paf";

    querySumPayment =
        "(SELECT SUM(v.amount) FROM PaymentAdjustedFee v WHERE v.payment.registration = t)";
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

    projectionBuilder().name(RegistrationDto.JSON_TOTAL_AMOUNT_AS_STRING)
        .tupleVariableName(amountVariableName)
        .expression(amountDynamicQuery.formatValueSum(amountVariableName))
        .resultConsumer((i, a) -> i.totalAmountAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(RegistrationDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING)
        .expression(amountDynamicQuery.formatRegistrationValuePartSum(amountVariableName))
        .resultConsumer((i, a) -> i.totalRegistrationAmountAsString = a.getNextAsLongFormatted())
        .build();

    projectionBuilder().name(RegistrationDto.JSON_PAID_AMOUNT_AS_STRING)
        .expressionFunction(p -> getSumPaymentQuery(p.projection()))
        .resultConsumer((i, a) -> i.paidAmountAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(RegistrationDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING)
        .expression(String.format("(CASE WHEN %1$s >= %2$s THEN %2$s ELSE %1$s END)",
            amountDynamicQuery.formatRegistrationValuePartSum(amountVariableName), querySumPayment))
        .resultConsumer((i, a) -> i.paidRegistrationAmountAsString = a.getNextAsLongFormatted())
        .build();

    projectionBuilder().name(RegistrationDto.JSON_PAYABLE_AMOUNT_AS_STRING)
        .expression(String.format("(%s - %s)",
            amountDynamicQuery.formatValueSum(amountVariableName), querySumPayment))
        .resultConsumer((i, a) -> i.payableAmountAsString = a.getNextAsLongFormatted()).build();

    projectionBuilder().name(RegistrationDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING)
        .expression(String.format("(CASE WHEN %1$s >= %2$s THEN %1$s - %2$s ELSE 0 END)",
            amountDynamicQuery.formatRegistrationValuePartSum(amountVariableName), querySumPayment))
        .resultConsumer((i, a) -> i.payableRegistrationAmountAsString = a.getNextAsLongFormatted())
        .build();

    // Jointures
    joinBuilder()
        .projectionsNames(RegistrationDto.JSON_TOTAL_AMOUNT_AS_STRING,
            RegistrationDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING)
        .with(AdjustedFee.class).tupleVariableName(adjustedFeeVariableName)
        .fieldName(AdjustedFee.FIELD_REGISTRATION).parentFieldName(null).leftInnerOrRight(true)
        .build();

    joinBuilder()
        .projectionsNames(RegistrationDto.JSON_TOTAL_AMOUNT_AS_STRING,
            RegistrationDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING)
        .with(Amount.class).tupleVariableName(amountVariableName)
        .parentTupleVariableName(adjustedFeeVariableName)
        .parentFieldName(AbstractAmountContainer.FIELD_AMOUNT).leftInnerOrRight(true).build();

    joinBuilder().disabledFunction(p -> hasTotalProjection(p.projection()))
        .projectionsNames(RegistrationDto.JSON_PAID_AMOUNT_AS_STRING,
            RegistrationDto.JSON_PAYABLE_AMOUNT_AS_STRING,
            RegistrationDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING,
            RegistrationDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING)
        .with(Payment.class).tupleVariableName(paymentVariableName)
        .fieldName(Payment.FIELD_REGISTRATION).parentFieldName(null).leftInnerOrRight(true).build();

    joinBuilder().disabledFunction(p -> hasTotalProjection(p.projection()))
        .projectionsNames(RegistrationDto.JSON_PAID_AMOUNT_AS_STRING,
            RegistrationDto.JSON_PAYABLE_AMOUNT_AS_STRING,
            RegistrationDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING,
            RegistrationDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING)
        .with(PaymentAdjustedFee.class).tupleVariableName(paymentAdjustedFeeVariableName)
        .fieldName(PaymentAdjustedFee.FIELD_PAYMENT).parentTupleVariableName(paymentVariableName)
        .parentFieldName(null).leftInnerOrRight(true).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }
  
  String getSumPaymentQuery(ProjectionDto projection) {
    return hasTotalProjection(projection) ? querySumPayment
        : formatSum(formatValueOrZeroIfNull(paymentAdjustedFeeVariableName,
            PaymentAdjustedFee.FIELD_AMOUNT));
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

  boolean hasTotalProjection(ProjectionDto projection) {
    return ProjectionDto.hasOneOfNames(projection, RegistrationDto.JSON_TOTAL_AMOUNT_AS_STRING,
        RegistrationDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING);
  }
}
