package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodableNamable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableCodableDto;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionDto;

/**
 * Cette classe représente la requête dynamique de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionDynamicQuery extends AbstractDynamicQuery<SubsidyDecision> {

  @Inject
  @Getter
  EntityManager entityManager;

  String branchVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public SubsidyDecisionDynamicQuery() {
    super(SubsidyDecision.class);
    branchVariableName = "branch";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_AMOUNT).fieldName(SubsidyDecision.FIELD_AMOUNT)
        .build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_SCHOOLING_IDENTIFIER)
        .fieldName(
            fieldName(SubsidyDecision.FIELD_SCHOOLING, AbstractIdentifiable.FIELD_IDENTIFIER))
        .nameFieldName(SubsidyDecision.FIELD_SCHOOLING_IDENTIFIER).build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_SCHOOLING_AS_STRING)
        .tupleVariableName(branchVariableName)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME)
        .nameFieldName(SubsidyDecision.FIELD_SCHOOLING_AS_STRING).build();

    projectionBuilder().name(SubsidyDecisionDto.JSON_AMOUNT_AS_STRING)
        .fieldName(SubsidyDecision.FIELD_AMOUNT)
        .nameFieldName(SubsidyDecision.FIELD_AMOUNT_AS_STRING).build();

    // Jointures
    joinBuilder().projectionsNames(SubsidyDecisionDto.JSON_SCHOOLING_AS_STRING)
        .entityClass(Branch.class).tupleVariableName(branchVariableName).parentFieldName(
            fieldName(SubsidyDecision.FIELD_SCHOOLING, Schooling.FIELD_BRANCH_IDENTIFIER))
        .build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }
}
