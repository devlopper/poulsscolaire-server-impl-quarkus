package org.cyk.system.poulsscolaire.server.impl.business.funding;

import ci.gouv.dgbf.extension.core.NumberHelper;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import ci.gouv.dgbf.extension.server.business.ResponseBuilder.Arguments;
import ci.gouv.dgbf.extension.server.service.api.response.IdentifiableResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingUpdateAmountRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingUpdateAmountResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingPersistence;

/**
 * Cette classe représente la mise à jour de montant de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingUpdateAmountBusiness extends AbstractIdentifiableUpdateBusiness<Funding,
    FundingPersistence, FundingValidator, FundingUpdateAmountRequestDto> {

  @Inject
  @Getter
  FundingPersistence persistence;

  @Inject
  @Getter
  FundingValidator validator;

  @Inject
  FundingDynamicQuery dynamicQuery;

  @Inject
  NumberHelper numberHelper;
  
  @Override
  protected void validate(FundingUpdateAmountRequestDto request, StringList messages,
      Funding funding) {
    super.validate(request, messages, funding);
    validationHelper.validateLowerThanByName(this, request.getAmount(), 0, "montant", "zéro",
        messages);
  }

  @Override
  protected void prepare(Funding funding, FundingUpdateAmountRequestDto request) {
    super.prepare(funding, request);
    funding.amount = request.getAmount();
  }

  @Override
  protected void listenAfterTransaction(Funding funding, FundingUpdateAmountRequestDto request) {
    super.listenAfterTransaction(funding, request);
    funding.amountAsString = numberHelper.format(dynamicQuery.sumAmount(request.getFilter()));
  }

  @Override
  protected IdentifiableResponseDto buildResponse(Funding funding, Arguments arguments) {
    FundingUpdateAmountResponseDto response =
        (FundingUpdateAmountResponseDto) super.buildResponse(funding, arguments);
    response.setTotalAmountAsString(funding.amountAsString);
    return response;
  }

  @Override
  protected Class<? extends IdentifiableResponseDto> getResponseClass() {
    return FundingUpdateAmountResponseDto.class;
  }
}
