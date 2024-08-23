package org.cyk.system.poulsscolaire.server.impl.business.identityrelationship;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService.IdentityRelationshipUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationshipPersistence;

/**
 * Cette classe représente la mise à jour de {@link IdentityRelationship}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityRelationshipUpdateBusiness extends
    AbstractIdentifiableUpdateBusiness<IdentityRelationship, IdentityRelationshipPersistence,
        IdentityRelationshipValidator, IdentityRelationshipUpdateRequestDto> {

  @Inject
  @Getter
  IdentityRelationshipPersistence persistence;

  @Inject
  @Getter
  IdentityRelationshipValidator validator;

  @Override
  protected void validate(IdentityRelationshipUpdateRequestDto request, StringList messages,
      IdentityRelationship identity) {
    super.validate(request, messages, identity);
    Object[] array = validator.validate(request, messages);
    identity.parent = (Identity) array[0];
    identity.child = (Identity) array[1];
  }

  @Override
  protected void prepare(IdentityRelationship identity,
      IdentityRelationshipUpdateRequestDto request) {
    super.prepare(identity, request);
    identity.set(request, null);
  }
}
