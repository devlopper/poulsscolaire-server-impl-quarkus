package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationFilter;

/**
 * Cette classe représente la requête dynamique de {@link SubsidyDecisionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionRegistrationDynamicQuery
    extends AbstractDynamicQuery<SubsidyDecisionRegistration> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public SubsidyDecisionRegistrationDynamicQuery() {
    super(SubsidyDecisionRegistration.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(SubsidyDecisionRegistrationDto.JSON_REJECTED)
        .fieldName(SubsidyDecisionRegistration.FIELD_REJECTED).build();

    projectionBuilder().name(SubsidyDecisionRegistrationDto.JSON_SUBSIDY_DECISION_IDENTIFIER)
        .nameFieldName(SubsidyDecisionRegistration.FIELD_SUBSIDY_DECISION_IDENTIFIER)
        .fieldName(fieldName(SubsidyDecisionRegistration.FIELD_SUBSIDY_DECISION,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();

    projectionBuilder().name(SubsidyDecisionRegistrationDto.JSON_SUBSIDY_DECISION_AS_STRING)
        .nameFieldName(SubsidyDecisionRegistration.FIELD_SUBSIDY_DECISION_AS_STRING)
        .fieldName(fieldName(SubsidyDecisionRegistration.FIELD_SUBSIDY_DECISION,
            AbstractIdentifiableCodable.FIELD_CODE))
        .build();

    projectionBuilder().name(SubsidyDecisionRegistrationDto.JSON_REJECTED_AS_STRING)
        .nameFieldName(SubsidyDecisionRegistration.FIELD_REJECTED_AS_STRING)
        .fieldName(SubsidyDecisionRegistration.FIELD_REJECTED).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(SubsidyDecisionRegistrationFilter.JSON_SUBSIDY_DECISION_IDENTIFIER)
        .fieldName(fieldName(SubsidyDecisionRegistration.FIELD_SUBSIDY_DECISION,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(SubsidyDecisionRegistrationFilter::getSubsidyDecisionIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(fieldName(SubsidyDecisionRegistration.FIELD_SUBSIDY_DECISION,
        AbstractIdentifiableCodable.FIELD_CODE)).build();
  }
}
