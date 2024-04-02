package org.cyk.system.poulsscolaire.server.impl.business.duegroup;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.DueGroupDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.DueGroup;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link DueGroup} et {@link DueGroupDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface DueGroupMapper extends IdentifiableMapper<DueGroup, DueGroupDto> {

}
