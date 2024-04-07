package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategoryPersistence;

/**
 * Cette class représente un validateur de {@link FeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeCategoryValidator extends AbstractIdentifiableCodableNamableValidator<FeeCategory> {

  @Inject
  @Getter
  private FeeCategoryPersistence persistence;

}
