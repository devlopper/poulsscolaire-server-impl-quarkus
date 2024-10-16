package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableNamableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodFilter;

/**
 * Cette classe représente la requête dynamique de {@link Period}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PeriodDynamicQuery extends AbstractDynamicQuery<Period> {

  @Inject
  @Getter
  EntityManager entityManager;

  String schoolPeriodVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public PeriodDynamicQuery() {
    super(Period.class);
    schoolPeriodVariableName = "sp";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    // Jointures
    joinBuilder().predicatesNames(PeriodFilter.JSON_SCHOOL_IDENTIFIER, PeriodFilter.JSON_OPENED)
        .with(SchoolPeriod.class).tupleVariableName(schoolPeriodVariableName)
        .fieldName(SchoolPeriod.FIELD_PERIOD_IDENTIFIER)
        .parentFieldName(AbstractIdentifiable.FIELD_IDENTIFIER).leftInnerOrRight(true).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(PeriodFilter.JSON_OPENED).tupleVariableName(schoolPeriodVariableName)
        .fieldName(SchoolPeriod.FIELD_OPENED).valueFunction(PeriodFilter::getOpened).build();

    predicateBuilder().name(PeriodFilter.JSON_SCHOOL_IDENTIFIER)
        .tupleVariableName(schoolPeriodVariableName).fieldName(SchoolPeriod.FIELD_SCHOOL_IDENTIFIER)
        .valueFunction(PeriodFilter::getSchoolIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();
  }
}
