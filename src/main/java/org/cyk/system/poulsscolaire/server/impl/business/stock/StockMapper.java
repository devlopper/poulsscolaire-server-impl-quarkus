package org.cyk.system.poulsscolaire.server.impl.business.stock;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.fee.StockDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Stock;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Stock} et {@link StockDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface StockMapper extends IdentifiableMapper<Stock, StockDto> {

}
