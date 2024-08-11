package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import ci.gouv.dgbf.extension.server.persistence.query.SingleResultGetter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link AmountDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountDeadlinePersistence extends AbstractIdentifiablePersistence<AmountDeadline> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public AmountDeadlinePersistence() {
    super(AmountDeadline.class);
    name = AmountDeadlineDto.NAME;
    pluralName = AmountDeadlineDto.PLURAL_NAME;
  }

  /**
   * Cette méthode permet d'obtenir les tableaux de {@link AmountDeadline} et {@link Fee}.
   *
   * @param fees {@link Fee}
   * @return tableaux de {@link AmountDeadline} et {@link Fee}
   */
  public List<Object[]> getByFees(Collection<Fee> fees) {
    return entityManager
        .createNamedQuery(AmountDeadline.QUERY_READ_BY_FEES_IDENTIFIER, Object[].class)
        .getResultList();
  }

  /**
   * Cette méthode permet d'obtenir la somme des paiements par montant.
   *
   * @param amount montant
   * @return somme des paiements
   */
  public long getPaymentSumByAmount(Amount amount) {
    return Optional.ofNullable(new SingleResultGetter<>(entityManager
        .createNamedQuery(AmountDeadline.QUERY_SUM_PAYMENT_BY_AMOUNT_IDENTIFIER, Long.class)
        .setParameter(AmountDeadline.FIELD_AMOUNT, amount)).noResultValue(0L).get()).orElse(0L);
  }

  /**
   * Cette méthode permet d'obtenir les {@link AmountDeadline} ayant paiement différent de zéro.
   *
   * @param registration {@link Registration}
   * @return {@link AmountDeadline} ayant paiement différent de zéro
   */
  public List<AmountDeadline> getWherePaymentNotZeroByRegistration(Registration registration) {
    return entityManager.createNamedQuery(
        AmountDeadline.QUERY_READ_WHERE_PAYMENT_NOT_ZERO_BY_REGISTRATION_IDENTIFIER,
        AmountDeadline.class).setParameter("registration", registration).getResultList();
  }
}
