package org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineGroupDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroup;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link DeadlineGroup} et {@link DeadlineGroupDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface DeadlineGroupMapper extends IdentifiableMapper<DeadlineGroup, DeadlineGroupDto> {

}
