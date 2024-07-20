package org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePaymentDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePaymentDeadlineDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePaymentDeadlinePersistence;

/**
 * Cette classe représente l'obtention de {@link AdjustedFeePaymentDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeePaymentDeadlineReadOneBusiness extends
    AbstractIdentifiableReadOneBusiness<AdjustedFeePaymentDeadline,
        AdjustedFeePaymentDeadlinePersistence, AdjustedFeePaymentDeadlineDynamicQuery,
        AdjustedFeePaymentDeadlineDto, AdjustedFeePaymentDeadlineMapper> {

  protected AdjustedFeePaymentDeadlineReadOneBusiness() {
    super(AdjustedFeePaymentDeadlineDto.class);
  }

  @Inject
  @Getter
  AdjustedFeePaymentDeadlinePersistence persistence;

  @Inject
  @Getter
  AdjustedFeePaymentDeadlineDynamicQuery dynamicQuery;

  @Inject
  @Getter
  AdjustedFeePaymentDeadlineMapper mapper;
}
