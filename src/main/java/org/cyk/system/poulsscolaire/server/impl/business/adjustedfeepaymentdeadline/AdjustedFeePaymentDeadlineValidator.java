package org.cyk.system.poulsscolaire.server.impl.business.adjustedfeepaymentdeadline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePaymentDeadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.AdjustedFeePaymentDeadlinePersistence;

/**
 * Cette class représente un validateur de {@link AdjustedFeePaymentDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeePaymentDeadlineValidator
    extends AbstractIdentifiableValidator<AdjustedFeePaymentDeadline> {

  @Inject
  @Getter
  private AdjustedFeePaymentDeadlinePersistence persistence;

}
