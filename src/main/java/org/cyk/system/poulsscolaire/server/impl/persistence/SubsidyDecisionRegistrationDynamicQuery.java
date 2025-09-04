package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.segregation.HasIsRejected;
import ci.gouv.dgbf.extension.core.segregation.HasIsRejectedAsString;
import ci.gouv.dgbf.extension.core.segregation.HasIsRejectedAsStringDto;
import ci.gouv.dgbf.extension.core.segregation.HasIsRejectedDto;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiableCodable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import ci.gouv.dgbf.extension.server.service.api.filter.IsRejectedFilter;
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

    projectionBuilder().name(SubsidyDecisionRegistrationDto.JSON_REGISTRATION_IDENTIFIER)
        .nameFieldName(SubsidyDecisionRegistration.FIELD_REGISTRATION_IDENTIFIER)
        .fieldName(fieldName(SubsidyDecisionRegistration.FIELD_REGISTRATION,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();

    projectionBuilder().name(SubsidyDecisionRegistrationDto.JSON_REGISTRATION_AS_STRING)
        .nameFieldName(SubsidyDecisionRegistration.FIELD_REGISTRATION_AS_STRING)
        .fieldName(fieldName(SubsidyDecisionRegistration.FIELD_REGISTRATION,
            AbstractIdentifiableCodable.FIELD_CODE))
        .build();

    projectionBuilder().name(HasIsRejectedDto.JSON_IS_REJECTED)
        .fieldName(HasIsRejected.FIELD_IS_REJECTED).build();

    projectionBuilder().name(HasIsRejectedAsStringDto.JSON_IS_REJECTED_AS_STRING)
        .fieldName(HasIsRejected.FIELD_IS_REJECTED)
        .nameFieldName(HasIsRejectedAsString.FIELD_IS_REJECTED_AS_STRING).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(SubsidyDecisionRegistrationFilter.JSON_SUBSIDY_DECISION_IDENTIFIER)
        .fieldName(fieldName(SubsidyDecisionRegistration.FIELD_SUBSIDY_DECISION,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(SubsidyDecisionRegistrationFilter::getSubsidyDecisionIdentifier).build();

    predicateBuilder().name(IsRejectedFilter.JSON_KEY).fieldName(HasIsRejected.FIELD_IS_REJECTED)
        .valueFunction(IsRejectedFilter::get).build();

    // Ordres par défaut
    orderBuilder().fieldName(fieldName(SubsidyDecisionRegistration.FIELD_SUBSIDY_DECISION,
        AbstractIdentifiableCodable.FIELD_CODE)).build();
  }
}
