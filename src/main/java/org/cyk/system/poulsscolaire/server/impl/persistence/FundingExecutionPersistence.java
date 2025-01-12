package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link FundingExecution}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class FundingExecutionPersistence extends AbstractIdentifiablePersistence<FundingExecution> {

  @Inject
  @Getter
  EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public FundingExecutionPersistence() {
    super(FundingExecution.class);
    name = FundingExecutionDto.NAME;
    pluralName = FundingExecutionDto.PLURAL_NAME;
  }
}
