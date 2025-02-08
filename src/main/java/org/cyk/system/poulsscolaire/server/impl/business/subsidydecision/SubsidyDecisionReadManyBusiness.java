package org.cyk.system.poulsscolaire.server.impl.business.subsidydecision;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionService.SubsidyDecisionGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionPersistence;

/**
 * Cette classe représente l'obtention de {@link SubsidyDecision}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<SubsidyDecision, SubsidyDecisionPersistence,
        SubsidyDecisionDynamicQuery, SubsidyDecisionDto, SubsidyDecisionMapper,
        SubsidyDecisionGetManyResponseDto> {

  protected SubsidyDecisionReadManyBusiness() {
    super(SubsidyDecisionGetManyResponseDto.class);
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
