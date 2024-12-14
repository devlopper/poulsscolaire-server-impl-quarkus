package org.cyk.system.poulsscolaire.server.impl.business.stockmovement;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovement;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link StockMovement} et
 * {@link StockMovementDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface StockMovementMapper
    extends IdentifiableMapper<StockMovement, StockMovementDto> {

}
