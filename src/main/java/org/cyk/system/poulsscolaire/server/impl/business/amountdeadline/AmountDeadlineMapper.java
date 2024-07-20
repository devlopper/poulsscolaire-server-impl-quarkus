package org.cyk.system.poulsscolaire.server.impl.business.amountdeadline;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadline;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link AmountDeadline} et
 * {@link AmountDeadlineDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface AmountDeadlineMapper
    extends IdentifiableMapper<AmountDeadline, AmountDeadlineDto> {

}
