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
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentFilter;

/**
 * Cette classe représente la requête dynamique de {@link SubsidyDecisionPayment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionPaymentDynamicQuery
    extends AbstractDynamicQuery<SubsidyDecisionPayment> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public SubsidyDecisionPaymentDynamicQuery() {
    super(SubsidyDecisionPayment.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(SubsidyDecisionPaymentDto.JSON_AMOUNT)
        .fieldName(SubsidyDecisionPayment.FIELD_AMOUNT).build();

    projectionBuilder().name(SubsidyDecisionPaymentDto.JSON_SUBSIDY_DECISION_IDENTIFIER)
        .nameFieldName(SubsidyDecisionPayment.FIELD_SUBSIDY_DECISION_IDENTIFIER)
        .fieldName(fieldName(SubsidyDecisionPayment.FIELD_SUBSIDY_DECISION,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();

    projectionBuilder().name(SubsidyDecisionPaymentDto.JSON_SUBSIDY_DECISION_AS_STRING)
        .nameFieldName(SubsidyDecisionPayment.FIELD_SUBSIDY_DECISION_AS_STRING)
        .fieldName(fieldName(SubsidyDecisionPayment.FIELD_SUBSIDY_DECISION,
            AbstractIdentifiableCodable.FIELD_CODE))
        .build();

    projectionBuilder().name(SubsidyDecisionPaymentDto.JSON_AMOUNT_AS_STRING)
        .nameFieldName(SubsidyDecisionPayment.FIELD_AMOUNT_AS_STRING)
        .fieldName(SubsidyDecisionPayment.FIELD_AMOUNT).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(SubsidyDecisionPaymentFilter.JSON_SUBSIDY_DECISION_IDENTIFIER)
        .fieldName(fieldName(SubsidyDecisionPayment.FIELD_SUBSIDY_DECISION,
            AbstractIdentifiable.FIELD_IDENTIFIER))
        .valueFunction(SubsidyDecisionPaymentFilter::getSubsidyDecisionIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(fieldName(SubsidyDecisionPayment.FIELD_SUBSIDY_DECISION,
        AbstractIdentifiableCodable.FIELD_CODE)).build();
  }
}
