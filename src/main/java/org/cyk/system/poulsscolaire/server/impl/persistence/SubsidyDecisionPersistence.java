package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiableCodableNamablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionPersistence
    extends AbstractIdentifiableCodableNamablePersistence<SubsidyDecision> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public SubsidyDecisionPersistence() {
    super(SubsidyDecision.class);
    name = SubsidyDecisionDto.NAME;
    pluralName = SubsidyDecisionDto.PLURAL_NAME;
  }
}
