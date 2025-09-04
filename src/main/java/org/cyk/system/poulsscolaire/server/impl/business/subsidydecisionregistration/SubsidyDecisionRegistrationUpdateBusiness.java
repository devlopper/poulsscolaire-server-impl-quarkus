package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationService.SubsidyDecisionRegistrationUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationValidator;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionRegistration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionRegistrationPersistence;

/**
 * Cette classe représente la mise à jour de {@link SubsidyDecisionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionRegistrationUpdateBusiness extends
    AbstractIdentifiableUpdateBusiness<SubsidyDecisionRegistration,
        SubsidyDecisionRegistrationPersistence, SubsidyDecisionRegistrationValidator,
        SubsidyDecisionRegistrationUpdateRequestDto> {

  @Inject
  @Getter
  SubsidyDecisionRegistrationPersistence persistence;

  @Inject
  @Getter
  SubsidyDecisionRegistrationValidator validator;

  @Inject
  SubsidyDecisionValidator subsidyDecisionValidator;

  @Inject
  RegistrationValidator registrationValidator;
  
  @Override
  protected void validate(SubsidyDecisionRegistrationUpdateRequestDto request, StringList messages,
      SubsidyDecisionRegistration subsidyDecision) {
    super.validate(request, messages, subsidyDecision);
    subsidyDecision.subsidyDecision = subsidyDecisionValidator
        .validateInstanceByIdentifier(request.getSubsidyDecisionIdentifier(), messages);
    subsidyDecision.registration = registrationValidator
        .validateInstanceByIdentifier(request.getRegistrationIdentifier(), messages);
    validator.validateIsRejected(request.getIsRejected(), messages);
  }

  @Override
  protected void prepare(SubsidyDecisionRegistration subsidyDecision,
      SubsidyDecisionRegistrationUpdateRequestDto request) {
    super.prepare(subsidyDecision, request);
    subsidyDecision.setIsRejected(request.getIsRejected());
  }
}
