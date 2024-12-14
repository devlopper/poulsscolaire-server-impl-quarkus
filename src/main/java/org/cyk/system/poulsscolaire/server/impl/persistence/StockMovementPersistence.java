package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link StockMovement}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StockMovementPersistence
    extends AbstractIdentifiablePersistence<StockMovement> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public StockMovementPersistence() {
    super(StockMovement.class);
    name = PaymentDto.NAME;
    pluralName = PaymentDto.PLURAL_NAME;
  }
}
