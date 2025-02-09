package org.cyk.system.poulsscolaire.server.impl.persistence;

import ci.gouv.dgbf.extension.server.persistence.AbstractIdentifiablePersistence;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentDto;

/**
 * Cette classe représente les fonctionnalités de persistance de {@link SubsidyDecisionPayment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionPaymentPersistence
    extends AbstractIdentifiablePersistence<SubsidyDecisionPayment> {

  @Inject
  @Getter
  private EntityManager entityManager;

  /**
   * Cette méthode permet de construire une instance.
   */
  public SubsidyDecisionPaymentPersistence() {
    super(SubsidyDecisionPayment.class);
    name = SubsidyDecisionPaymentDto.NAME;
    pluralName = SubsidyDecisionPaymentDto.PLURAL_NAME;
  }
}
