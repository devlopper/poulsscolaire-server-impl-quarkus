package org.cyk.system.poulsscolaire.server.impl.business.fundingsource;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSource;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSourceDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSourcePersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link FundingSource}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingSourceReadByIdentifierBusiness
    extends AbstractIdentifiableReadByIdentifierBusiness<FundingSource, FundingSourcePersistence,
        FundingSourceDynamicQuery, FundingSourceDto, FundingSourceMapper> {

  protected FundingSourceReadByIdentifierBusiness() {
    super(FundingSourceDto.class);
  }

  @Inject
  @Getter
  FundingSourcePersistence persistence;

  @Inject
  @Getter
  FundingSourceDynamicQuery dynamicQuery;

  @Inject
  @Getter
  FundingSourceMapper mapper;
}
