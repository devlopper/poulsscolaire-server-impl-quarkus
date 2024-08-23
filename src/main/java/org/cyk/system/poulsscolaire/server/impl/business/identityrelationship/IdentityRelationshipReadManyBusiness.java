package org.cyk.system.poulsscolaire.server.impl.business.identityrelationship;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipService.IdentityRelationshipGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationshipDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationshipPersistence;

/**
 * Cette classe représente l'obtention de {@link IdentityRelationship}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityRelationshipReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<IdentityRelationship, IdentityRelationshipPersistence,
        IdentityRelationshipDynamicQuery, IdentityRelationshipDto, IdentityRelationshipMapper,
        IdentityRelationshipGetManyResponseDto> {

  protected IdentityRelationshipReadManyBusiness() {
    super(IdentityRelationshipGetManyResponseDto.class);
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
