package org.cyk.system.poulsscolaire.server.impl.business.gender;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.configuration.GenderDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Gender} et {@link GenderDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface GenderMapper extends IdentifiableMapper<Gender, GenderDto> {

}
