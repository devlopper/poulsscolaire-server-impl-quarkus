package org.cyk.system.poulsscolaire.server.impl.business.department;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.configuration.DepartmentDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Department;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Department} et {@link DepartmentDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface DepartmentMapper extends IdentifiableMapper<Department, DepartmentDto> {
  
}
