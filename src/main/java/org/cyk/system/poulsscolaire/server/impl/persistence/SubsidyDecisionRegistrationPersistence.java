package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationDto;

/**
 * Cette classe représente les fonctionnalités de persistance de
 * {@link SubsidyDecisionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionRegistrationPersistence
    extends AbstractIdentifiablePersistence<SubsidyDecisionRegistration> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public SubsidyDecisionRegistrationPersistence() {
    super(SubsidyDecisionRegistration.class);
    name = SubsidyDecisionRegistrationDto.NAME;
    pluralName = SubsidyDecisionRegistrationDto.PLURAL_NAME;
  }
}
