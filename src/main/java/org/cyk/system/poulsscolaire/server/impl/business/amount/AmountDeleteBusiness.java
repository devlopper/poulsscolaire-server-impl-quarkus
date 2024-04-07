package org.cyk.system.poulsscolaire.server.impl.business.amount;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;

/**
 * Cette classe représente la suppression de {@link Amount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    Amount, AmountPersistence, AmountValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  AmountPersistence persistence;

  @Inject
  @Getter
  AmountValidator validator;
}
