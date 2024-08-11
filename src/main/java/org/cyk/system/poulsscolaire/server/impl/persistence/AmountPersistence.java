package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Amount}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountPersistence extends AbstractIdentifiablePersistence<Amount> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public AmountPersistence() {
    super(Amount.class);
    name = AmountDto.NAME;
    pluralName = AmountDto.PLURAL_NAME;
  }

  /**
   * Cette méthode permet d'obtenir les montants ayant une valeur différente de zéro.
   *
   * @param registration inscription
   * @return montants ayant une valeur différente de zéro
   */
  public List<Amount> getWhereValueNotZeroByRegistration(Registration registration) {
    return entityManager
        .createNamedQuery(Amount.QUERY_READ_WHERE_VALUE_NOT_ZERO_BY_REGISTRATION_IDENTIFIER,
            Amount.class)
        .setParameter("registration", registration).getResultList();
  }
}
