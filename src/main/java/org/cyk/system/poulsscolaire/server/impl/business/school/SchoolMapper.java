package org.cyk.system.poulsscolaire.server.impl.business.school;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.School;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link School} et {@link SchoolDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface SchoolMapper extends IdentifiableMapper<School, SchoolDto> {
  
}
