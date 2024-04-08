package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link FeeCategory} et {@link FeeCategoryDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface FeeCategoryMapper extends IdentifiableMapper<FeeCategory, FeeCategoryDto> {

}
