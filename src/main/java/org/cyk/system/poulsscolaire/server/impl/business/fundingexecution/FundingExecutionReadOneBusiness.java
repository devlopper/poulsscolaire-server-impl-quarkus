package org.cyk.system.poulsscolaire.server.impl.business.fundingexecution;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecution;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecutionDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecutionPersistence;

/**
 * Cette classe représente l'obtention de {@link FundingExecution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingExecutionReadOneBusiness
    extends AbstractIdentifiableReadOneBusiness<FundingExecution, FundingExecutionPersistence,
        FundingExecutionDynamicQuery, FundingExecutionDto, FundingExecutionMapper> {

  protected FundingExecutionReadOneBusiness() {
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
