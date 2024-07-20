package org.cyk.system.poulsscolaire.server.impl.business.amountdeadline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadlineDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadlinePersistence;

/**
 * Cette classe représente l'obtention de {@link AmountDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountDeadlineReadOneBusiness extends
    AbstractIdentifiableReadOneBusiness<AmountDeadline,
        AmountDeadlinePersistence, AmountDeadlineDynamicQuery,
        AmountDeadlineDto, AmountDeadlineMapper> {

  protected AmountDeadlineReadOneBusiness() {
    super(AmountDeadlineDto.class);
  }

  @Inject
  @Getter
  AmountDeadlinePersistence persistence;

  @Inject
  @Getter
  AmountDeadlineDynamicQuery dynamicQuery;

  @Inject
  @Getter
  AmountDeadlineMapper mapper;
}
