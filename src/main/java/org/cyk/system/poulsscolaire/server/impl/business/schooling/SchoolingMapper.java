package org.cyk.system.poulsscolaire.server.impl.business.schooling;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Schooling} et {@link SchoolingDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface SchoolingMapper extends IdentifiableMapper<Schooling, SchoolingDto> {

}
