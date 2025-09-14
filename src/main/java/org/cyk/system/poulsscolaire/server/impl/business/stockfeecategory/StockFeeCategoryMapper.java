package org.cyk.system.poulsscolaire.server.impl.business.stockfeecategory;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.fee.StockFeeCategoryDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockFeeCategory;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link StockFeeCategory} et
 * {@link StockFeeCategoryDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface StockFeeCategoryMapper
    extends IdentifiableMapper<StockFeeCategory, StockFeeCategoryDto> {

}
