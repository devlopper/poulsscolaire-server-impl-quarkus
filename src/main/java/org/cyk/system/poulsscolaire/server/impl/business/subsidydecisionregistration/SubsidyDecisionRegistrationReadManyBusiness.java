package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationDto;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationService.SubsidyDecisionRegistrationGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionRegistration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionRegistrationDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionRegistrationPersistence;

/**
 * Cette classe représente l'obtention de {@link SubsidyDecisionRegistration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SubsidyDecisionRegistrationReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<SubsidyDecisionRegistration,
        SubsidyDecisionRegistrationPersistence, SubsidyDecisionRegistrationDynamicQuery,
        SubsidyDecisionRegistrationDto, SubsidyDecisionRegistrationMapper,
        SubsidyDecisionRegistrationGetManyResponseDto> {

  @Inject
  @Getter
  SubsidyDecisionRegistrationPersistence persistence;

  @Inject
  @Getter
  SubsidyDecisionRegistrationDynamicQuery dynamicQuery;

  @Inject
  @Getter
  SubsidyDecisionRegistrationMapper mapper;

  /**
   * Cette méthode permet de construire.
   */
  protected SubsidyDecisionRegistrationReadManyBusiness() {
    super(SubsidyDecisionRegistrationGetManyResponseDto.class);
  }
}
