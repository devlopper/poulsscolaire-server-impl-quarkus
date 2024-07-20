package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link AmountDeadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AmountDeadlinePersistence
    extends AbstractIdentifiablePersistence<AmountDeadline> {

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
}
