package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationFilter;

/**
 * Cette classe représente la requête dynamique de {@link SchoolConfiguration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolConfigurationDynamicQuery extends AbstractDynamicQuery<SchoolConfiguration> {

  @Inject
  @Getter
  EntityManager entityManager;

  String schoolVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public SchoolConfigurationDynamicQuery() {
    super(SchoolConfiguration.class);
    schoolVariableName = "s";
  }

  @PostConstruct
  void postConstruct() {
    // Projections
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(SchoolConfigurationDto.JSON_SCHOOL_IDENTIFIER)
        .fieldName(SchoolConfiguration.FIELD_SCHOOL_IDENTIFIER).build();

    projectionBuilder().name(SchoolConfigurationDto.JSON_SCHOOL_AS_STRING)
        .nameFieldName(SchoolConfiguration.FIELD_SCHOOL_AS_STRING)
        .tupleVariableName(schoolVariableName).fieldName(AbstractIdentifiableNamable.FIELD_NAME)
        .build();

    projectionBuilder().name(SchoolConfigurationDto.JSON_PAYMENT_ACCOUNTING_ACCOUNT_IDENTIFIER)
        .nameFieldName(SchoolConfiguration.FIELD_PAYMENT_ACCOUNTING_ACCOUNT_IDENTIFIER)
        .fieldName(fieldName(SchoolConfiguration.FIELD_PAYMENT_ACCOUNTING_ACCOUNT,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();

    projectionBuilder().name(SchoolConfigurationDto.JSON_PAYMENT_ACCOUNTING_ACCOUNT_AS_STRING)
        .nameFieldName(SchoolConfiguration.FIELD_PAYMENT_ACCOUNTING_ACCOUNT_AS_STRING)
        .expression(formatConcatCodeName(
            fieldName(variableName, SchoolConfiguration.FIELD_PAYMENT_ACCOUNTING_ACCOUNT)))
        .resultConsumer((i, a) -> i.paymentAccountingAccountAsString = a.getNextAsString()).build();

    // Jointures
    joinBuilder().projectionsNames(SchoolConfigurationDto.JSON_SCHOOL_AS_STRING)
        .predicatesNames(SchoolConfigurationDto.JSON_SCHOOL_IDENTIFIER)
        .entityName(School.ENTITY_NAME).tupleVariableName(schoolVariableName)
        .parentFieldName(SchoolConfiguration.FIELD_SCHOOL_IDENTIFIER).leftInnerOrRight(true)
        .build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(SchoolConfigurationFilter.JSON_SCHOOL_IDENTIFIER)
        .tupleVariableName(schoolVariableName).fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(SchoolConfigurationFilter::getSchoolIdentifier).build();

    // Ordres par défaut
  }
}
