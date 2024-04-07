package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.FeeCategoryDto;
import org.cyk.system.poulsscolaire.server.api.FeeCategoryService.GetManyResponseDto;
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
        FeeCategoryDynamicQuery, FeeCategoryDto, FeeCategoryMapper, GetManyResponseDto> {

  protected FeeCategoryReadManyBusiness() {
    super(GetManyResponseDto.class);
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
