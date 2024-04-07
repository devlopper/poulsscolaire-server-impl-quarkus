package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.FeeCategoryDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategoryDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategoryPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link FeeCategory}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeeCategoryReadByIdentifierBusiness
    extends AbstractIdentifiableReadByIdentifierBusiness<FeeCategory, FeeCategoryPersistence,
        FeeCategoryDynamicQuery, FeeCategoryDto, FeeCategoryMapper> {

  protected FeeCategoryReadByIdentifierBusiness() {
    super(FeeCategoryDto.class);
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
