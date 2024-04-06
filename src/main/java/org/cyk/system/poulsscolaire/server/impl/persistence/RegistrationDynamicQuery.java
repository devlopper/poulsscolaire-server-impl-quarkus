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
import org.cyk.system.poulsscolaire.server.api.RegistrationDto;

/**
 * Cette classe représente la requête dynamique de {@link Registration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class RegistrationDynamicQuery extends AbstractDynamicQuery<Registration> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public RegistrationDynamicQuery() {
    super(Registration.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(RegistrationDto.JSON_STUDENT_AS_STRING)
        .nameFieldName(Registration.FIELD_STUDENT_AS_STRING)
        .fieldName(fieldName(Registration.FIELD_STUDENT, Student.FIELD_IDENTITY,
            Identity.FIELD_FIRST_NAME))
        .build();

    projectionBuilder().name(RegistrationDto.JSON_SCHOOLING_AS_STRING)
        .nameFieldName(Registration.FIELD_SCHOOLING_AS_STRING)
        .fieldName(fieldName(Registration.FIELD_SCHOOLING, AbstractIdentifiableCodable.FIELD_CODE))
        .build();

    projectionBuilder().name(RegistrationDto.JSON_ASSIGNMENT_TYPE_AS_STRING)
        .nameFieldName(Registration.FIELD_ASSIGNMENT_TYPE_AS_STRING)
        .fieldName(fieldName(Registration.FIELD_ASSIGNMENT_TYPE,
            AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(RegistrationDto.JSON_SENIORITY_AS_STRING)
        .nameFieldName(Registration.FIELD_SENIORITY_AS_STRING)
        .fieldName(
            fieldName(Registration.FIELD_SENIORITY, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }
}
