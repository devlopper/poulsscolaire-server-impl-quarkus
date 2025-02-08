package org.cyk.system.poulsscolaire.server.impl.business.subsidydecision;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPersistence;

/**
 * Cette classe représente la suppression de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    SubsidyDecision, SubsidyDecisionPersistence, SubsidyDecisionValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  SubsidyDecisionPersistence persistence;

  @Inject
  @Getter
  SubsidyDecisionValidator validator;
}
