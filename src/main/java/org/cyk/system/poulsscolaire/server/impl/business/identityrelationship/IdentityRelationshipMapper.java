package org.cyk.system.poulsscolaire.server.impl.business.identityrelationship;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityRelationship;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link IdentityRelationship} et
 * {@link IdentityRelationshipDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface IdentityRelationshipMapper
    extends IdentifiableMapper<IdentityRelationship, IdentityRelationshipDto> {

}
