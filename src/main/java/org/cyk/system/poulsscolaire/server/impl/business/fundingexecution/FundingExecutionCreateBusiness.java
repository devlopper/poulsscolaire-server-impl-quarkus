package org.cyk.system.poulsscolaire.server.impl.business.fundingexecution;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDateTime;
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
    validate(request.getAmount(), messages);
    return new Object[] {funding};
  }

  public void validate(Integer amount, StringList messages) {
    validator.validateAmount(amount, messages);
  }

  @Override
  protected void setFields(FundingExecution fundingExecution, Object[] array,
      FundingExecutionCreateRequestDto request) {
    super.setFields(fundingExecution, array, request);
    setFields(fundingExecution, (Funding) array[0], request.getDate(), request.getAmount());
  }

  /**
   * Cette méthode permet d'assigner les champs.
   *
   * @param fundingExecution {@link FundingExecution}
   * @param funding {@link Funding}
   * @param date date
   * @param amount montant
   */
  public void setFields(FundingExecution fundingExecution, Funding funding, LocalDateTime date,
      Integer amount) {
    fundingExecution.funding = funding;
    fundingExecution.date = date;
    fundingExecution.amount = amount;
  }
}
