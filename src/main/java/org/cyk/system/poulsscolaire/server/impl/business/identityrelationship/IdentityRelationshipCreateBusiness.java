package org.cyk.system.poulsscolaire.server.impl.business.identityrelationship;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService.IdentityRelationshipCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationshipPersistence;

/**
 * Cette classe représente la création de {@link IdentityRelationship}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityRelationshipCreateBusiness extends
    AbstractIdentifiableCreateBusiness<IdentityRelationship, IdentityRelationshipPersistence,
        IdentityRelationshipValidator, IdentityRelationshipCreateRequestDto> {

  @Inject
  @Getter
  IdentityRelationshipPersistence persistence;

  @Inject
  @Getter
  IdentityRelationshipValidator validator;

  @Override
  protected Object[] validate(IdentityRelationshipCreateRequestDto request, StringList messages) {
    return validator.validate(request, messages);
  }

  @Override
  protected void setFields(IdentityRelationship identity, Object[] array,
      IdentityRelationshipCreateRequestDto request) {
    super.setFields(identity, array, request);
    identity.set(request, array);
  }
}
