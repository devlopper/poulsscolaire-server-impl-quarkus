package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPayment;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPaymentPersistence;

/**
 * Cette class représente un validateur de {@link SubsidyDecisionPayment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionPaymentValidator
    extends AbstractIdentifiableValidator<SubsidyDecisionPayment> {

  @Inject
  @Getter
  private SubsidyDecisionPaymentPersistence persistence;

}
