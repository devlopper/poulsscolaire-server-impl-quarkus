package org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfiguration;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link SchoolConfiguration} et
 * {@link SchoolConfigurationDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface SchoolConfigurationMapper
    extends IdentifiableMapper<SchoolConfiguration, SchoolConfigurationDto> {

}
