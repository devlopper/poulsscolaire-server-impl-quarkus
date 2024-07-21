package org.cyk.system.poulsscolaire.server.impl.business.amount;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AmountService.AmountCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;

/**
 * Cette classe représente la création d'un {@link Amount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountCreateBusiness extends AbstractIdentifiableCreateBusiness<Amount,
    AmountPersistence, AmountValidator, AmountCreateRequestDto> {

  @Inject
  @Getter
  AmountPersistence persistence;

  @Inject
  @Getter
  AmountValidator validator;

  @Override
  protected void setFields(Amount amount, Object[] array, AmountCreateRequestDto request) {
    super.setFields(amount, array, request);
    amount.set(request, array);
  }
}
