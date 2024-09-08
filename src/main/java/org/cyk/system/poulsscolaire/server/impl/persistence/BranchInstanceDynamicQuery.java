package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableNamableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceFilter;

/**
 * Cette classe représente la requête dynamique de {@link BranchInstance}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchInstanceDynamicQuery extends AbstractDynamicQuery<BranchInstance> {

  @Inject
  @Getter
  EntityManager entityManager;

  String schoolingVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public BranchInstanceDynamicQuery() {
    super(BranchInstance.class);
    schoolingVariableName = "s";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableNamable.FIELD_NAME).build();

    // Jointures
    joinBuilder().predicatesNames(BranchInstanceFilter.JSON_SCHOOLING_IDENTIFIER)
        .entityClass(Schooling.class)
        .expression("JOIN Schooling s ON s.schoolIdentifier = t.schoolIdentifier "
            + "AND s.branchIdentifier = t.branchIdentifier")
        .build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(BranchInstanceFilter.JSON_SCHOOL_IDENTIFIER)
        .fieldName(BranchInstance.FIELD_SCHOOL_IDENTIFIER)
        .valueFunction(BranchInstanceFilter::getSchoolIdentifier).build();

    predicateBuilder().name(BranchInstanceFilter.JSON_BRANCH_IDENTIFIER)
        .fieldName(BranchInstance.FIELD_BRANCH_IDENTIFIER)
        .valueFunction(BranchInstanceFilter::getBranchIdentifier).build();

    predicateBuilder().name(BranchInstanceFilter.JSON_SCHOOLING_IDENTIFIER)
        .tupleVariableName(schoolingVariableName).fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(BranchInstanceFilter::getSchoolingIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();
  }
}
