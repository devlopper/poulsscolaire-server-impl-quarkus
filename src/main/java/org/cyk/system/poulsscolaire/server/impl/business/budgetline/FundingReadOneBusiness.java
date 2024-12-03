package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;

/**
 * Cette classe représente l'obtention de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingReadOneBusiness
    extends AbstractIdentifiableReadOneBusiness<Funding, FundingPersistence,
        FundingDynamicQuery, FundingDto, FundingMapper> {

  protected FundingReadOneBusiness() {
    super(FundingDto.class);
  }

  @Inject
  @Getter
  FundingPersistence persistence;

  @Inject
  @Getter
  FundingDynamicQuery dynamicQuery;

  @Inject
  @Getter
  FundingMapper mapper;
}
