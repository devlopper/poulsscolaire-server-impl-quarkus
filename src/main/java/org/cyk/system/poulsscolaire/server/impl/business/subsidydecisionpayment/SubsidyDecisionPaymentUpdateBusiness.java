package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentService.SubsidyDecisionPaymentUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPayment;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPaymentPersistence;

/**
 * Cette classe représente la mise à jour de {@link SubsidyDecisionPayment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionPaymentUpdateBusiness extends
    AbstractIdentifiableUpdateBusiness<SubsidyDecisionPayment, SubsidyDecisionPaymentPersistence,
        SubsidyDecisionPaymentValidator, SubsidyDecisionPaymentUpdateRequestDto> {

  @Inject
  @Getter
  SubsidyDecisionPaymentPersistence persistence;

  @Inject
  @Getter
  SubsidyDecisionPaymentValidator validator;

  @Inject
  SubsidyDecisionValidator subsidyDecisionValidator;

  @Override
  protected void validate(SubsidyDecisionPaymentUpdateRequestDto request, StringList messages,
      SubsidyDecisionPayment subsidyDecision) {
    super.validate(request, messages, subsidyDecision);
    subsidyDecision.subsidyDecision = subsidyDecisionValidator
        .validateInstanceByIdentifier(request.getSubsidyDecisionIdentifier(), messages);
  }

  @Override
  protected void prepare(SubsidyDecisionPayment subsidyDecision,
      SubsidyDecisionPaymentUpdateRequestDto request) {
    super.prepare(subsidyDecision, request);
    subsidyDecision.amount = request.getAmount();
  }
}
