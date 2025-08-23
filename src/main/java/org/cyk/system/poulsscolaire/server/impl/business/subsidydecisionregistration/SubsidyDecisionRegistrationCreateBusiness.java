package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationService.SubsidyDecisionRegistrationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.registration.RegistrationValidator;
import org.cyk.system.poulsscolaire.server.impl.business.subsidydecision.SubsidyDecisionValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionRegistration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionRegistrationPersistence;

/**
 * Cette classe représente la création de {@link SubsidyDecisionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionRegistrationCreateBusiness extends
    AbstractIdentifiableCreateBusiness<SubsidyDecisionRegistration,
        SubsidyDecisionRegistrationPersistence, SubsidyDecisionRegistrationValidator,
        SubsidyDecisionRegistrationCreateRequestDto> {

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
  protected Object[] validate(SubsidyDecisionRegistrationCreateRequestDto request,
      StringList messages) {
    SubsidyDecision subsidyDecision = subsidyDecisionValidator
        .validateInstanceByIdentifier(request.getSubsidyDecisionIdentifier(), messages);
    Registration registration = registrationValidator
        .validateInstanceByIdentifier(request.getRegistrationIdentifier(), messages);
    return new Object[] {subsidyDecision, registration};
  }

  @Override
  protected void setFields(SubsidyDecisionRegistration subsidyDecision, Object[] array,
      SubsidyDecisionRegistrationCreateRequestDto request) {
    super.setFields(subsidyDecision, array, request);
    subsidyDecision.subsidyDecision = (SubsidyDecision) array[0];
    subsidyDecision.registration = (Registration) array[1];
    subsidyDecision.accepted = request.getAccepted();
  }
}
