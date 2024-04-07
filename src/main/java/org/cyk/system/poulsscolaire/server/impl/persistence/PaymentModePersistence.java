package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodableNamablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link PaymentMode}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PaymentModePersistence
    extends AbstractIdentifiableCodableNamablePersistence<PaymentMode> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public PaymentModePersistence() {
    super(PaymentMode.class);
    name = "mode de paiement";
    pluralName = "modes de paiement";
  }
}
