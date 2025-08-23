package org.cyk.system.poulsscolaire.server.impl.business.subsidydecisionregistration;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionRegistrationDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecisionRegistration;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link SubsidyDecisionRegistration} et
 * {@link SubsidyDecisionRegistrationDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface SubsidyDecisionRegistrationMapper
    extends IdentifiableMapper<SubsidyDecisionRegistration, SubsidyDecisionRegistrationDto> {

}
