package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<Funding,
        FundingPersistence, FundingDynamicQuery, FundingDto,
        FundingMapper> {

  protected FundingReadByIdentifierBusiness() {
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
