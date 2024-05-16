package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.Getter;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link AdjustedFee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AdjustedFeePersistence extends AbstractIdentifiablePersistence<AdjustedFee> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public AdjustedFeePersistence() {
    super(AdjustedFee.class);
    name = "frais ajusté";
    pluralName = "frais ajustés";
  }

  /**
   * Cette méthode permet d'obtenir les frais ajustés à payer par inscription.
   *
   * @param registration inscription
   * @return les frais ajustés à payer
   */
  public List<Object[]> getForPaymentByRegistration(Registration registration) {
    return entityManager
        .createNamedQuery(AdjustedFee.QUERY_READ_FOR_PAYMENT_BY_REGISTRATION_IDENTIFIER,
            Object[].class)
        .setParameter(AdjustedFee.FIELD_REGISTRATION, registration).getResultList();
  }
}
