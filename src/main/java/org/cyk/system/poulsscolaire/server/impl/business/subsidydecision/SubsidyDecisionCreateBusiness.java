package org.cyk.system.poulsscolaire.server.impl.business.subsidydecision;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionService.SubsidyDecisionCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPersistence;

/**
 * Cette classe représente la création de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionCreateBusiness
    extends AbstractIdentifiableCreateBusiness<SubsidyDecision, SubsidyDecisionPersistence,
        SubsidyDecisionValidator, SubsidyDecisionCreateRequestDto> {

  @Inject
  @Getter
  SubsidyDecisionPersistence persistence;

  @Inject
  @Getter
  SubsidyDecisionValidator validator;

  @Inject
  SchoolingValidator schoolingValidator;

  @Override
  protected Object[] validate(SubsidyDecisionCreateRequestDto request, StringList messages) {
    Schooling schooling =
        schoolingValidator.validateInstanceByIdentifier(request.getSchoolingIdentifier(), messages);
    validator.validateAmount(request.getAmount(), messages);
    validator.validateDate(request.getDate(), messages);
    return new Object[] {schooling};
  }

  @Override
  protected void setFields(SubsidyDecision subsidyDecision, Object[] array,
      SubsidyDecisionCreateRequestDto request) {
    super.setFields(subsidyDecision, array, request);
    subsidyDecision.schooling = (Schooling) array[0];
    subsidyDecision.amount = request.getAmount();
    subsidyDecision.setDate(request.getDate());
  }
}
