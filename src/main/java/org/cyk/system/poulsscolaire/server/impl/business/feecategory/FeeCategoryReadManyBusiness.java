package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryService.FeeCategoryGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategoryDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategoryPersistence;

/**
 * Cette classe représente l'obtention de {@link FeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeCategoryReadManyBusiness
    extends AbstractIdentifiableReadManyBusiness<FeeCategory, FeeCategoryPersistence,
        FeeCategoryDynamicQuery, FeeCategoryDto, FeeCategoryMapper, FeeCategoryGetManyResponseDto> {

  protected FeeCategoryReadManyBusiness() {
    super(FeeCategoryGetManyResponseDto.class);
  }

  @Inject
  @Getter
  FeeCategoryPersistence persistence;

  @Inject
  @Getter
  FeeCategoryDynamicQuery dynamicQuery;

  @Inject
  @Getter
  FeeCategoryMapper mapper;
}
