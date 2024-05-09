package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingDto;

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

  /**
   * Cette méthode permet d'instancier un object.
   */
  public SchoolingDynamicQuery() {
    super(Schooling.class);
    schoolVariableName = "s";
    branchVariableName = "b";
    periodVariableName = "p";
  }

  @PostConstruct
  void postConstruct() {
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

    joinBuilder().projectionsNames(SchoolingDto.JSON_SCHOOL_AS_STRING)
        .entityName(School.ENTITY_NAME).tupleVariableName(schoolVariableName)
        .parentFieldName(Schooling.FIELD_SCHOOL_IDENTIFIER).leftInnerOrRight(true).build();

    joinBuilder().projectionsNames(SchoolingDto.JSON_BRANCH_AS_STRING)
        .entityName(Branch.ENTITY_NAME).tupleVariableName(branchVariableName)
        .parentFieldName(Schooling.FIELD_BRANCH_IDENTIFIER).leftInnerOrRight(true).build();

    joinBuilder().projectionsNames(SchoolingDto.JSON_PERIOD_AS_STRING)
        .entityName(Period.ENTITY_NAME).tupleVariableName(periodVariableName)
        .parentFieldName(Schooling.FIELD_PERIOD_IDENTIFIER).leftInnerOrRight(true).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }
}
