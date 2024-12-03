package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Funding} et
 * {@link FundingDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface FundingMapper
    extends IdentifiableMapper<Funding, FundingDto> {

}
