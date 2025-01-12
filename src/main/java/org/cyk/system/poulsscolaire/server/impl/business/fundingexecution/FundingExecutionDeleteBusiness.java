package org.cyk.system.poulsscolaire.server.impl.business.fundingexecution;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecution;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecutionPersistence;

/**
 * Cette classe représente la suppression de {@link FundingExecution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingExecutionDeleteBusiness
    extends AbstractIdentifiableDeleteBusiness<FundingExecution, FundingExecutionPersistence,
        FundingExecutionValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  FundingExecutionPersistence persistence;

  @Inject
  @Getter
  FundingExecutionValidator validator;
}
