package org.cyk.system.poulsscolaire.server.impl.business.stockdistributionregistration;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.fee.StockDistributionRegistrationDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDistributionRegistration;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link StockDistributionRegistration} et
 * {@link StockDistributionRegistrationDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface StockDistributionRegistrationMapper
    extends IdentifiableMapper<StockDistributionRegistration, StockDistributionRegistrationDto> {

}
