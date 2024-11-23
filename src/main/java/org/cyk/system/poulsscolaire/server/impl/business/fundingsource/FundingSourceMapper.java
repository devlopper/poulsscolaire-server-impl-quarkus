package org.cyk.system.poulsscolaire.server.impl.business.fundingsource;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSource;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link FundingSource} et {@link FundingSourceDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface FundingSourceMapper extends IdentifiableMapper<FundingSource, FundingSourceDto> {

}
