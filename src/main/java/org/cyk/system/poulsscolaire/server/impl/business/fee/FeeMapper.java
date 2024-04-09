package org.cyk.system.poulsscolaire.server.impl.business.fee;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.fee.FeeDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Fee;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Fee} et {@link FeeDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface FeeMapper extends IdentifiableMapper<Fee, FeeDto> {

}
