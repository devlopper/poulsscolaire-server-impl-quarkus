package org.cyk.system.poulsscolaire.server.impl.business.period;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Period;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Period} et {@link PeriodDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface PeriodMapper extends IdentifiableMapper<Period, PeriodDto> {
  
}
