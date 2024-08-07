package org.cyk.system.poulsscolaire.server.impl.persistence;

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
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchFilter;

/**
 * Cette classe représente la requête dynamique de {@link Branch}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchDynamicQuery extends AbstractDynamicQuery<Branch> {

  @Inject
  @Getter
  EntityManager entityManager;

  String schoolBranchVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public BranchDynamicQuery() {
    super(Branch.class);
    schoolBranchVariableName = "sb";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    // Jointures
    joinBuilder().predicatesNames(BranchFilter.JSON_SCHOOL_IDENTIFIER).with(SchoolBranch.class)
        .tupleVariableName(schoolBranchVariableName)
        .fieldName(SchoolBranch.FIELD_BRANCH_IDENTIFIER)
        .parentFieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(BranchFilter.JSON_SCHOOL_IDENTIFIER)
        .tupleVariableName(schoolBranchVariableName).fieldName(SchoolBranch.FIELD_SCHOOL_IDENTIFIER)
        .valueFunction(BranchFilter::getSchoolIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();
  }
}
