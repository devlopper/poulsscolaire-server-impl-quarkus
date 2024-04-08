package org.cyk.system.poulsscolaire.server.impl.business.deadline;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Deadline} et {@link DeadlineDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface DeadlineMapper extends IdentifiableMapper<Deadline, DeadlineDto> {

}
