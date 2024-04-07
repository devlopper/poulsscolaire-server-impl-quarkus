package org.cyk.system.poulsscolaire.server.impl.business.amount;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.AmountDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Amount} et {@link AmountDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface AmountMapper extends IdentifiableMapper<Amount, AmountDto> {

}
