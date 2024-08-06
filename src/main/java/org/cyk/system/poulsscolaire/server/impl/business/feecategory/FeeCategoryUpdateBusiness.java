package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryService.FeeCategoryUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategoryPersistence;

/**
 * Cette classe représente la mise à jour de {@link FeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeCategoryUpdateBusiness extends AbstractIdentifiableUpdateBusiness<FeeCategory,
    FeeCategoryPersistence, FeeCategoryValidator, FeeCategoryUpdateRequestDto> {

  @Inject
  @Getter
  FeeCategoryPersistence persistence;

  @Inject
  @Getter
  FeeCategoryValidator validator;

  @Override
  protected void validate(FeeCategoryUpdateRequestDto request, StringList messages,
      FeeCategory feeCategory) {
    super.validate(request, messages, feeCategory);
    validator.validateSchoolCode(request.getSchoolIdentifier(), request.getCode(), messages);
  }

  @Override
  protected void prepare(FeeCategory feeCategory, FeeCategoryUpdateRequestDto request) {
    super.prepare(feeCategory, request);
    feeCategory.schoolIdentifier = request.getSchoolIdentifier();
  }
}
