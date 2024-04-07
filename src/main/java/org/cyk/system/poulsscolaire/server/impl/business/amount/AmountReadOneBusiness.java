package org.cyk.system.poulsscolaire.server.impl.business.amount;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.AmountDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountPersistence;

/**
 * Cette classe représente l'obtention de {@link Amount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountReadOneBusiness extends AbstractIdentifiableReadOneBusiness<Amount,
    AmountPersistence, AmountDynamicQuery, AmountDto, AmountMapper> {

  protected AmountReadOneBusiness() {
    super(AmountDto.class);
  }

  @Inject
  @Getter
  AmountPersistence persistence;

  @Inject
  @Getter
  AmountDynamicQuery dynamicQuery;

  @Inject
  @Getter
  AmountMapper mapper;
}
