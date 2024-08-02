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
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineDto;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineFilter;

/**
 * Cette classe représente la requête dynamique de {@link Deadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineDynamicQuery extends AbstractDynamicQuery<Deadline> {

  @Inject
  @Getter
  EntityManager entityManager;

  String schoolVariableName;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public DeadlineDynamicQuery() {
    super(Deadline.class);
    schoolVariableName = "s";
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(AbstractIdentifiableCodableDto.JSON_CODE)
        .fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();

    projectionBuilder().name(AbstractIdentifiableCodableNamableDto.JSON_NAME)
        .fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();

    projectionBuilder().name(DeadlineDto.JSON_GROUP_AS_STRING)
        .nameFieldName(Deadline.FIELD_GROUP_AS_STRING)
        .fieldName(fieldName(Deadline.FIELD_GROUP, AbstractIdentifiableCodableNamable.FIELD_NAME))
        .build();

    projectionBuilder().name(DeadlineDto.JSON_DATE_AS_STRING)
        .nameFieldName(Deadline.FIELD_DATE_AS_STRING).fieldName(Deadline.FIELD_DATE).build();

    projectionBuilder().name(DeadlineDto.JSON_SCHOOL_IDENTIFIER)
        .fieldName(Deadline.FIELD_SCHOOL_IDENTIFIER).build();

    projectionBuilder().name(DeadlineDto.JSON_SCHOOL_AS_STRING)
        .expression(formatConcatName(schoolVariableName))
        .resultConsumer((i, a) -> i.schoolAsString = a.getNextAsString()).build();

    // Jointures
    joinBuilder().projectionsNames(DeadlineDto.JSON_SCHOOL_AS_STRING)
        .predicatesNames(DeadlineDto.JSON_SCHOOL_IDENTIFIER).entityName(School.ENTITY_NAME)
        .tupleVariableName(schoolVariableName).parentFieldName(Deadline.FIELD_SCHOOL_IDENTIFIER)
        .leftInnerOrRight(true).build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(DeadlineFilter.JSON_SCHOOL_IDENTIFIER)
        .fieldName(Deadline.FIELD_SCHOOL_IDENTIFIER)
        .valueFunction(DeadlineFilter::getSchoolIdentifier).build();

    // Ordres par défaut
    orderBuilder().fieldName(AbstractIdentifiableCodableNamable.FIELD_NAME).build();
    orderBuilder().fieldName(AbstractIdentifiableCodable.FIELD_CODE).build();
  }
}
