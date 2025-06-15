package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.core.segregation.HasDistributionAsString;
import ci.gouv.dgbf.extension.core.segregation.HasDistributionAsStringDto;
import ci.gouv.dgbf.extension.core.segregation.HasDistributionIdentifier;
import ci.gouv.dgbf.extension.core.segregation.HasDistributionIdentifierDto;
import ci.gouv.dgbf.extension.core.segregation.HasQuantity;
import ci.gouv.dgbf.extension.core.segregation.HasQuantityAsString;
import ci.gouv.dgbf.extension.core.segregation.HasQuantityAsStringDto;
import ci.gouv.dgbf.extension.core.segregation.HasQuantityDto;
import ci.gouv.dgbf.extension.core.segregation.HasRegistrationAsString;
import ci.gouv.dgbf.extension.core.segregation.HasRegistrationAsStringDto;
import ci.gouv.dgbf.extension.core.segregation.HasRegistrationIdentifier;
import ci.gouv.dgbf.extension.core.segregation.HasRegistrationIdentifierDto;
import ci.gouv.dgbf.extension.server.persistence.Jpql;
import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;

/**
 * Cette classe représente la requête dynamique de {@link StockDistributionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockDistributionRegistrationDynamicQuery
    extends AbstractDynamicQuery<StockDistributionRegistration> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public StockDistributionRegistrationDynamicQuery() {
    super(StockDistributionRegistration.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(HasDistributionIdentifierDto.JSON_DISTRIBUTION_IDENTIFIER)
        .nameFieldName(HasDistributionIdentifier.FIELD_DISTRIBUTION_IDENTIFIER)
        .fieldName(Jpql.identifierOf(StockDistributionRegistration.FIELD_DISTRIBUTION)).build();

    projectionBuilder().name(HasDistributionAsStringDto.JSON_DISTRIBUTION_AS_STRING)
        .nameFieldName(HasDistributionAsString.FIELD_DISTRIBUTION_AS_STRING)
        .fieldName(Jpql.codeOf(StockDistributionRegistration.FIELD_DISTRIBUTION)).build();

    projectionBuilder().name(HasRegistrationIdentifierDto.JSON_REGISTRATION_IDENTIFIER)
        .nameFieldName(HasRegistrationIdentifier.FIELD_REGISTRATION_IDENTIFIER)
        .fieldName(Jpql.identifierOf(StockDistributionRegistration.FIELD_REGISTRATION)).build();

    projectionBuilder().name(HasRegistrationAsStringDto.JSON_REGISTRATION_AS_STRING)
        .nameFieldName(HasRegistrationAsString.FIELD_REGISTRATION_AS_STRING)
        .fieldName(Jpql.codeOf(StockDistributionRegistration.FIELD_REGISTRATION)).build();

    projectionBuilder().name(HasQuantityDto.JSON_QUANTITY).nameFieldName(HasQuantity.FIELD_QUANTITY)
        .fieldName(Jpql.quantityOf(StockDistributionRegistration.FIELD_MOVEMENT)).build();

    projectionBuilder().name(HasQuantityAsStringDto.JSON_QUANTITY_AS_STRING)
        .nameFieldName(HasQuantityAsString.FIELD_QUANTITY_AS_STRING)
        .fieldName(Jpql.quantityOf(StockDistributionRegistration.FIELD_MOVEMENT)).build();

    // Jointures


    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();



    // Ordres par défaut
  }
}
