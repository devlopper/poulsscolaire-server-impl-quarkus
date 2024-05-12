package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
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
        .setParameter(Fee.QUERY_PARAMETER_SCHOOLING, schooling).getResultList();
  }
}
