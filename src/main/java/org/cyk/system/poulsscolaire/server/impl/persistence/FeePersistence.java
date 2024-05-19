package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import ci.gouv.dgbf.extension.server.persistence.query.SingleResultGetter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Fee}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FeePersistence extends AbstractIdentifiablePersistence<Fee> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public FeePersistence() {
    super(Fee.class);
    name = "frais";
    pluralName = name;
  }

  /**
   * Cette méthode permet d'obtenir les frais par scolarité.
   *
   * @param schooling scolarité
   * @return les frais
   */
  public List<Fee> getBySchooling(Schooling schooling) {
    return entityManager.createNamedQuery(Fee.QUERY_READ_BY_SCHOOLING_IDENTIFIER, Fee.class)
        .setParameter(Fee.FIELD_SCHOOLING, schooling).getResultList();
  }

  /**
   * Cette méthode permet de savoir si le numéro d'ordre de paiement existe.
   *
   * @param schooling scolarité
   * @param assignmentType type d'affectation
   * @param seniority ancienneté
   * @param paymentOrderNumber numéro d'ordre de paiement
   * @return vrai ou faux
   */
  public Boolean isPaymentOrderNumberExist(Schooling schooling, AssignmentType assignmentType,
      Seniority seniority, Integer paymentOrderNumber) {
    return Optional
        .ofNullable(new SingleResultGetter<>(entityManager
            .createNamedQuery(Fee.QUERY_IS_PAYMENT_ORDER_NUMBER_EXIST_IDENTIFIER, Boolean.class)
            .setParameter(Fee.FIELD_SCHOOLING, schooling)
            .setParameter(Fee.FIELD_ASSIGNMENT_TYPE, assignmentType)
            .setParameter(Fee.FIELD_SENIORITY, seniority)
            .setParameter(Amount.FIELD_PAYMENT_ORDER_NUMBER, paymentOrderNumber)).get())
        .orElse(false);
  }
}
