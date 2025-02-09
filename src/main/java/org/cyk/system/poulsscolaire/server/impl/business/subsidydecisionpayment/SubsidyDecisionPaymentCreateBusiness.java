package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionpayment;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionPaymentService.SubsidyDecisionPaymentCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPayment;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPaymentPersistence;

/**
 * Cette classe représente la création de {@link SubsidyDecisionPayment}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionPaymentCreateBusiness extends
    AbstractIdentifiableCreateBusiness<SubsidyDecisionPayment, SubsidyDecisionPaymentPersistence,
        SubsidyDecisionPaymentValidator, SubsidyDecisionPaymentCreateRequestDto> {

  @Inject
  @Getter
  SubsidyDecisionPaymentPersistence persistence;

  @Inject
  @Getter
  SubsidyDecisionPaymentValidator validator;

  @Inject
  SubsidyDecisionValidator subsidyDecisionValidator;

  @Override
  protected Object[] validate(SubsidyDecisionPaymentCreateRequestDto request, StringList messages) {
    SubsidyDecision subsidyDecision = subsidyDecisionValidator
        .validateInstanceByIdentifier(request.getSubsidyDecisionIdentifier(), messages);
    return new Object[] {subsidyDecision};
  }

  @Override
  protected void setFields(SubsidyDecisionPayment subsidyDecision, Object[] array,
      SubsidyDecisionPaymentCreateRequestDto request) {
    super.setFields(subsidyDecision, array, request);
    subsidyDecision.subsidyDecision = (SubsidyDecision) array[0];
    subsidyDecision.amount = request.getAmount();
  }
}
