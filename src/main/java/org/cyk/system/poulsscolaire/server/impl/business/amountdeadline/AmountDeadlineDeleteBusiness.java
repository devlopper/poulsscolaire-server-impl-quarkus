package org.cyk.system.poulsscolaire.server.impl.business.amountdeadline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.AmountDeadlinePersistence;

/**
 * Cette classe représente la suppression de {@link AmountDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountDeadlineDeleteBusiness extends
    AbstractIdentifiableDeleteBusiness<AmountDeadline,
        AmountDeadlinePersistence, AmountDeadlineValidator,
        DeleteOneRequestDto> {

  @Inject
  @Getter
  AmountDeadlinePersistence persistence;

  @Inject
  @Getter
  AmountDeadlineValidator validator;
}
