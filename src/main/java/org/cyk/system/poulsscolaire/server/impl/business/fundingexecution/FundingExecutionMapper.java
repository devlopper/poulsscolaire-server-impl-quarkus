package org.cyk.system.poulsscolaire.server.impl.business.fundingexecution;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecution;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link FundingExecution} et
 * {@link FundingExecutionDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface FundingExecutionMapper
    extends IdentifiableMapper<FundingExecution, FundingExecutionDto> {

}
