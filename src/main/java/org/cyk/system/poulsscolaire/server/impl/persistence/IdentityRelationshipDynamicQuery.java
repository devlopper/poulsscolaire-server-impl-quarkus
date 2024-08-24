package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.entity.AbstractIdentifiable;
import ci.gouv.dgbf.extension.server.persistence.query.AbstractDynamicQuery;
import ci.gouv.dgbf.extension.server.service.api.AbstractIdentifiableFilter;
import ci.gouv.dgbf.extension.server.service.api.entity.AbstractIdentifiableDto;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipType;

/**
 * Cette classe représente la requête dynamique de {@link IdentityRelationship}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityRelationshipDynamicQuery extends AbstractDynamicQuery<IdentityRelationship> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public IdentityRelationshipDynamicQuery() {
    super(IdentityRelationship.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(IdentityRelationshipDto.JSON_PARENT_IDENTIFIER)
        .nameFieldName(IdentityRelationship.FIELD_PARENT_IDENTIFIER)
        .fieldName(
            fieldName(IdentityRelationship.FIELD_PARENT, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();

    projectionBuilder().name(IdentityRelationshipDto.JSON_PARENT_AS_STRING)
        .nameFieldName(IdentityRelationship.FIELD_PARENT_AS_STRING)
        .expression(formatConcat(
            fieldName(variableName, IdentityRelationship.FIELD_PARENT, Identity.FIELD_FIRST_NAME),
            "' '",
            fieldName(variableName, IdentityRelationship.FIELD_PARENT, Identity.FIELD_LAST_NAMES)))
        .resultConsumer((i, a) -> i.parentAsString = a.getNextAsString()).build();

    projectionBuilder().name(IdentityRelationshipDto.JSON_CHILD_IDENTIFIER)
        .nameFieldName(IdentityRelationship.FIELD_CHILD_IDENTIFIER)
        .fieldName(
            fieldName(IdentityRelationship.FIELD_CHILD, AbstractIdentifiable.FIELD_IDENTIFIER))
        .build();

    projectionBuilder().name(IdentityRelationshipDto.JSON_CHILD_AS_STRING)
        .nameFieldName(IdentityRelationship.FIELD_CHILD_AS_STRING)
        .expression(formatConcat(
            fieldName(variableName, IdentityRelationship.FIELD_CHILD, Identity.FIELD_FIRST_NAME),
            "' '",
            fieldName(variableName, IdentityRelationship.FIELD_CHILD, Identity.FIELD_LAST_NAMES)))
        .resultConsumer((i, a) -> i.childAsString = a.getNextAsString()).build();

    projectionBuilder().name(IdentityRelationshipDto.JSON_TYPE_AS_STRING).expression("t.type")
        .resultConsumer(
            (i, a) -> i.typeAsString = a.getNextAs(IdentityRelationshipType.class).getName())
        .build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();
  }
}
