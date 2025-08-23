package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionRegistration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionRegistrationPersistence;

/**
 * Cette classe représente la suppression de {@link SubsidyDecisionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionRegistrationDeleteBusiness extends
    AbstractIdentifiableDeleteBusiness<SubsidyDecisionRegistration,
        SubsidyDecisionRegistrationPersistence, SubsidyDecisionRegistrationValidator,
        DeleteOneRequestDto> {

  @Inject
  @Getter
  SubsidyDecisionRegistrationPersistence persistence;

  @Inject
  @Getter
  SubsidyDecisionRegistrationValidator validator;
}
