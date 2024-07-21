package org.cyk.system.poulsscolaire.server.impl.business.amount;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AmountService.AmountUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;

/**
 * Cette classe représente la mise à jour de {@link Amount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountUpdateBusiness extends AbstractIdentifiableUpdateBusiness<Amount,
    AmountPersistence, AmountValidator, AmountUpdateRequestDto> {

  @Inject
  @Getter
  AmountPersistence persistence;

  @Inject
  @Getter
  AmountValidator validator;

  @Override
  protected void prepare(Amount amount, AmountUpdateRequestDto request) {
    super.prepare(amount, request);
    amount.set(request, null);
  }
}
