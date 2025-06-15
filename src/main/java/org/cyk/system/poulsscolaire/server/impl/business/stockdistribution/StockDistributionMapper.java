package org.cyk.system.poulsscolaire.server.impl.business.stockdistribution;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistribution;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link StockDistribution} et
 * {@link StockDistributionDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface StockDistributionMapper
    extends IdentifiableMapper<StockDistribution, StockDistributionDto> {

}
