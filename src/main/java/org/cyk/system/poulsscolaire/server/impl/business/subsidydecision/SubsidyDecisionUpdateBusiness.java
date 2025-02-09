package org.cyk.system.poulsscolaire.server.impl.business.subsidydecision;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionService.SubsidyDecisionUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPersistence;

/**
 * Cette classe représente la mise à jour de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionUpdateBusiness
    extends AbstractIdentifiableUpdateBusiness<SubsidyDecision, SubsidyDecisionPersistence,
        SubsidyDecisionValidator, SubsidyDecisionUpdateRequestDto> {

  @Inject
  @Getter
  SubsidyDecisionPersistence persistence;

  @Inject
  @Getter
  SubsidyDecisionValidator validator;

  @Inject
  SchoolingValidator schoolingValidator;

  @Override
  protected void validate(SubsidyDecisionUpdateRequestDto request, StringList messages,
      SubsidyDecision subsidyDecision) {
    super.validate(request, messages, subsidyDecision);
    subsidyDecision.schooling =
        schoolingValidator.validateInstanceByIdentifier(request.getSchoolingIdentifier(), messages);
  }

  @Override
  protected void prepare(SubsidyDecision subsidyDecision, SubsidyDecisionUpdateRequestDto request) {
    super.prepare(subsidyDecision, request);
    subsidyDecision.amount = request.getAmount();
  }
}
