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

/**
 * Cette classe représente la requête dynamique de {@link DeadlineGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineGroupDynamicQuery extends AbstractDynamicQuery<DeadlineGroup> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public DeadlineGroupDynamicQuery() {
    super(DeadlineGroup.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }
}
