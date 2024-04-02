package org.cyk.system.poulsscolaire.server.impl.business.seniority;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.SeniorityDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Seniority;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Seniority} et {@link SeniorityDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface SeniorityMapper extends IdentifiableMapper<Seniority, SeniorityDto> {

}
