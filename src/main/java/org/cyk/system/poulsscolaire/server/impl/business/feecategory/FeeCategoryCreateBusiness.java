package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.FeeCategoryService.FeeCategoryCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategoryPersistence;

/**
 * Cette classe représente la création d'un {@link FeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeCategoryCreateBusiness extends AbstractIdentifiableCreateBusiness<FeeCategory,
    FeeCategoryPersistence, FeeCategoryValidator, FeeCategoryCreateRequestDto> {

  @Inject
  @Getter
  FeeCategoryPersistence persistence;

  @Inject
  @Getter
  FeeCategoryValidator validator;
}
