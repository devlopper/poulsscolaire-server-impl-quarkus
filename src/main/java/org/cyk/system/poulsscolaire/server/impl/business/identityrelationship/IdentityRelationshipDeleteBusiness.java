package org.cyk.system.poulsscolaire.server.impl.business.identityrelationship;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationshipPersistence;

/**
 * Cette classe représente la suppression de {@link IdentityRelationship}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityRelationshipDeleteBusiness
    extends AbstractIdentifiableDeleteBusiness<IdentityRelationship,
        IdentityRelationshipPersistence, IdentityRelationshipValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  IdentityRelationshipPersistence persistence;

  @Inject
  @Getter
  IdentityRelationshipValidator validator;
}
