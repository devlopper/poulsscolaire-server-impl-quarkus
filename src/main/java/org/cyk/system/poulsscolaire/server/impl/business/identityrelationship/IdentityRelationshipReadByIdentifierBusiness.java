package org.cyk.system.poulsscolaire.server.impl.business.identityrelationship;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationshipDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationshipPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link IdentityRelationship}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityRelationshipReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<IdentityRelationship,
        IdentityRelationshipPersistence, IdentityRelationshipDynamicQuery, IdentityRelationshipDto,
        IdentityRelationshipMapper> {

  protected IdentityRelationshipReadByIdentifierBusiness() {
    super(IdentityRelationshipDto.class);
  }

  @Inject
  @Getter
  IdentityRelationshipPersistence persistence;

  @Inject
  @Getter
  IdentityRelationshipDynamicQuery dynamicQuery;

  @Inject
  @Getter
  IdentityRelationshipMapper mapper;
}
