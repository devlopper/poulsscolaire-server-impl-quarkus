package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link Funding}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingPersistence extends AbstractIdentifiablePersistence<Funding> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public FundingPersistence() {
    super(Funding.class);
    name = FundingDto.NAME;
    pluralName = FundingDto.PLURAL_NAME;
  }
}
