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
import org.cyk.system.poulsscolaire.server.api.registration.IdentityDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityFilter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipType;

/**
 * Cette classe représente la requête dynamique de {@link Identity}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityDynamicQuery extends AbstractDynamicQuery<Identity> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet d'instancier un object.
   */
  public IdentityDynamicQuery() {
    super(Identity.class);
  }

  @PostConstruct
  void postConstruct() {
    projectionBuilder().name(AbstractIdentifiableDto.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER).build();

    projectionBuilder().name(IdentityDto.JSON_FIRST_NAME).fieldName(Identity.FIELD_FIRST_NAME)
        .build();

    projectionBuilder().name(IdentityDto.JSON_LAST_NAMES).fieldName(Identity.FIELD_LAST_NAMES)
        .build();

    projectionBuilder().name(IdentityDto.JSON_PHONE_NUMBER).fieldName(Identity.FIELD_PHONE_NUMBER)
        .build();

    projectionBuilder().name(IdentityDto.JSON_EMAIL_ADDRESS).fieldName(Identity.FIELD_EMAIL_ADDRESS)
        .build();

    projectionBuilder().name(IdentityDto.JSON_RELATIONSHIP_TYPE_PARENT_AS_STRING)
        .expression("(SELECT ir.type FROM IdentityRelationship ir WHERE ir.parent = t)")
        .resultConsumer((i, a) -> i.relationshipTypeParentAsString =
            a.getNextAs(IdentityRelationshipType.class).getName())
        .build();

    projectionBuilder().name(AbstractIdentifiableDto.JSON_AS_STRING)
        .expression("t.firstName, t.lastNames")
        .resultConsumer((i, a) -> i.asString = a.getNextAsString() + " " + a.getNextAsString())
        .build();

    // Prédicats
    predicateBuilder().name(AbstractIdentifiableFilter.JSON_IDENTIFIER)
        .fieldName(AbstractIdentifiable.FIELD_IDENTIFIER)
        .valueFunction(AbstractIdentifiableFilter::getIdentifier).build();

    predicateBuilder().name(IdentityFilter.JSON_RELATIONSHIP_CHILD_IDENTIFIER)
        .expression("EXISTS(SELECT ir FROM IdentityRelationship ir WHERE ir.parent = t "
            + "AND ir.child.identifier = :idEnfantRelation)")
        .valueFunction(IdentityFilter::getRelationshipChildIdentifier).build();

    // Ordres par défaut
  }
}
