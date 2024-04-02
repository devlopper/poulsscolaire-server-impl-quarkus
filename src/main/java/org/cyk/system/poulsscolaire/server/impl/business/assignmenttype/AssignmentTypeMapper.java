package org.cyk.system.poulsscolaire.server.impl.business.assignmenttype;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.AssignmentTypeDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link AssignmentType} et {@link AssignmentTypeDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface AssignmentTypeMapper
    extends IdentifiableMapper<AssignmentType, AssignmentTypeDto> {

}
