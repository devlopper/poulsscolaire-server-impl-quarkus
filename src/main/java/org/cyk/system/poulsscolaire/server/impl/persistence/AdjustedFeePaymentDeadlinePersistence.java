package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link AdjustedFeePaymentDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeePaymentDeadlinePersistence
    extends AbstractIdentifiablePersistence<AdjustedFeePaymentDeadline> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public AdjustedFeePaymentDeadlinePersistence() {
    super(AdjustedFeePaymentDeadline.class);
    name = AdjustedFeePaymentDeadlineDto.NAME;
    pluralName = AdjustedFeePaymentDeadlineDto.PLURAL_NAME;
  }
}
