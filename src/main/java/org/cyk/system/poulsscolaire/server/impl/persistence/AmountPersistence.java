package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import ci.gouv.dgbf.extension.server.persistence.query.SingleResultGetter;
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

  /**
   * Cette méthode permet d'obtenir la sommation de montant par scolarité, type d'affectation et
   * ancienneté.
   *
   * @param schooling {@link Schooling}
   * @param assignmentType {@link AssignmentType}
   * @param seniority {@link Seniority}
   * @return sommation de montant
   */
  public long getValueSumBySchoolingByAssignmentTypeBySeniority(Schooling schooling,
      AssignmentType assignmentType, Seniority seniority) {
    return new SingleResultGetter<>(entityManager
        .createNamedQuery(
            Amount.QUERY_SUM_VALUE_BY_SCHOOLING_BY_ASSIGNMENT_TYPE_BY_SENIORITY_IDENTIFIER,
            Long.class)
        .setParameter("schooling", schooling).setParameter("assignmentType", assignmentType)
        .setParameter("seniority", seniority)).noResultValue(0L).get();
  }
}
