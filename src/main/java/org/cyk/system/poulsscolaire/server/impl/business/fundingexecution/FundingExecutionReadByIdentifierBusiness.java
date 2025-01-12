package org.cyk.system.poulsscolaire.server.impl.business.fundingexecution;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecution;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecutionDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecutionPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link FundingExecution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingExecutionReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<FundingExecution,
        FundingExecutionPersistence, FundingExecutionDynamicQuery, FundingExecutionDto,
        FundingExecutionMapper> {

  protected FundingExecutionReadByIdentifierBusiness() {
    super(FundingExecutionDto.class);
  }

  @Inject
  @Getter
  FundingExecutionPersistence persistence;

  @Inject
  @Getter
  FundingExecutionDynamicQuery dynamicQuery;

  @Inject
  @Getter
  FundingExecutionMapper mapper;
}
