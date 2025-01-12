package org.cyk.system.poulsscolaire.server.impl.business.fundingexecution;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionService.FundingExecutionUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecution;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecutionPersistence;

/**
 * Cette classe représente la mise à jour de {@link FundingExecution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingExecutionUpdateBusiness
    extends AbstractIdentifiableUpdateBusiness<FundingExecution, FundingExecutionPersistence,
        FundingExecutionValidator, FundingExecutionUpdateRequestDto> {

  @Inject
  @Getter
  FundingExecutionPersistence persistence;

  @Inject
  @Getter
  FundingExecutionValidator validator;


}
