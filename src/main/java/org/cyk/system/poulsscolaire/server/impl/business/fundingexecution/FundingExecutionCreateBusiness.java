package org.cyk.system.poulsscolaire.server.impl.business.fundingexecution;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionService.FundingExecutionCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecution;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecutionPersistence;

/**
 * Cette classe représente la création de {@link FundingExecution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingExecutionCreateBusiness
    extends AbstractIdentifiableCreateBusiness<FundingExecution, FundingExecutionPersistence,
        FundingExecutionValidator, FundingExecutionCreateRequestDto> {

  @Inject
  @Getter
  FundingExecutionPersistence persistence;

  @Inject
  @Getter
  FundingExecutionValidator validator;

  @Inject
  FundingValidator fundingValidator;

  @Override
  protected Object[] validate(FundingExecutionCreateRequestDto request, StringList messages) {
    Funding funding =
        fundingValidator.validateInstanceByIdentifier(request.getFundingIdentifier(), messages);
    return new Object[] {funding};
  }

  @Override
  protected void setFields(FundingExecution fundingExecution, Object[] array,
      FundingExecutionCreateRequestDto request) {
    super.setFields(fundingExecution, array, request);
    fundingExecution.funding = (Funding) array[0];
    fundingExecution.amount = request.getAmount();
  }
}
