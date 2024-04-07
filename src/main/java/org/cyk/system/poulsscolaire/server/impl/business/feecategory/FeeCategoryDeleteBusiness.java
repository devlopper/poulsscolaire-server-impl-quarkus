package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategoryPersistence;

/**
 * Cette classe représente la suppression de {@link FeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeCategoryDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    FeeCategory, FeeCategoryPersistence, FeeCategoryValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  FeeCategoryPersistence persistence;

  @Inject
  @Getter
  FeeCategoryValidator validator;
}
