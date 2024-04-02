package org.cyk.system.poulsscolaire.server.impl.business.identity;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.IdentityDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Identity} et {@link IdentityDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface IdentityMapper extends IdentifiableMapper<Identity, IdentityDto> {

}
