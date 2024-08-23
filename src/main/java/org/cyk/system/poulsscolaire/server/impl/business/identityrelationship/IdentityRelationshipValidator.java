package org.cyk.system.poulsscolaire.server.impl.business.identityrelationship;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationshipPersistence;

/**
 * Cette class représente un validateur de {@link IdentityRelationship}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityRelationshipValidator
    extends AbstractIdentifiableValidator<IdentityRelationship> {

  @Inject
  @Getter
  private IdentityRelationshipPersistence persistence;

  @Inject
  IdentityValidator identityValidator;

  /**
   * Cette méthode permet de valider.
   *
   * @param request requête
   * @param messages messages
   * @return tableau
   */
  public Object[] validate(IdentityRelationshipService.IdentityRelationshipSaveRequest request,
      StringList messages) {
    Identity parent =
        identityValidator.validateInstanceByIdentifier(request.getParentIdentifier(), messages);
    Identity child =
        identityValidator.validateInstanceByIdentifier(request.getChildIdentifier(), messages);
    return new Object[] {parent, child};
  }
}
