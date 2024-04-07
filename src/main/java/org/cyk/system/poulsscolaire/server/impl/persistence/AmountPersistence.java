package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;

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
    name = "montant";
    pluralName = "montants";
  }
}
