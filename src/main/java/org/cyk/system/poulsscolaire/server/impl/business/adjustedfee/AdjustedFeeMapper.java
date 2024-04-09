package org.cyk.system.poulsscolaire.server.impl.business.adjustedfee;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFee;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link AdjustedFee} et {@link AdjustedFeeDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface AdjustedFeeMapper extends IdentifiableMapper<AdjustedFee, AdjustedFeeDto> {

}
