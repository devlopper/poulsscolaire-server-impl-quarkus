package org.cyk.system.poulsscolaire.server.impl.business.identity;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipType;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentityCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationshipPersistence;

/**
 * Cette classe représente la création de {@link Identity}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityCreateBusiness extends AbstractIdentifiableCreateBusiness<Identity,
    IdentityPersistence, IdentityValidator, IdentityCreateRequestDto> {

  @Inject
  @Getter
  IdentityPersistence persistence;

  @Inject
  @Getter
  IdentityValidator validator;

  @Inject
  IdentityRelationshipPersistence identityRelationshipPersistence;

  @Override
  protected Object[] validate(IdentityCreateRequestDto request, StringList messages) {
    return validator.validate(request, messages);
  }

  @Override
  protected void setFields(Identity identity, Object[] array, IdentityCreateRequestDto request) {
    super.setFields(identity, array, request);
    identity.set(request, array);
  }

  @Override
  protected void doTransact(Identity identity) {
    super.doTransact(identity);
    Core.runIfNotNull(identity.relationshipParent,
        () -> createRelationship(identity.relationshipParent, identity.relationshipType, identity,
            identity.audit));

    Core.runIfNotNull(identity.relationshipChild, () -> createRelationship(identity,
        identity.relationshipType, identity.relationshipChild, identity.audit));
  }

  void createRelationship(Identity parent, IdentityRelationshipType type, Identity child,
      Audit audit) {
    IdentityRelationship relationship = new IdentityRelationship();
    relationship.generateIdentifier();
    relationship.parent = parent;
    relationship.type = type;
    relationship.child = child;
    relationship.audit = audit;
    identityRelationshipPersistence.create(relationship);
  }
}
