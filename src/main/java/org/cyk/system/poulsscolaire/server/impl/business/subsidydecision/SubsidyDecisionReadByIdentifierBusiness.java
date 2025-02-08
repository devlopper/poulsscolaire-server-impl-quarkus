package org.cyk.system.poulsscolaire.server.impl.business.subsidydecision;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<SubsidyDecision, SubsidyDecisionPersistence,
        SubsidyDecisionDynamicQuery, SubsidyDecisionDto, SubsidyDecisionMapper> {

  protected SubsidyDecisionReadByIdentifierBusiness() {
    super(SubsidyDecisionDto.class);
  }

  @Inject
  @Getter
  SubsidyDecisionPersistence persistence;

  @Inject
  @Getter
  SubsidyDecisionDynamicQuery dynamicQuery;

  @Inject
  @Getter
  SubsidyDecisionMapper mapper;
}
