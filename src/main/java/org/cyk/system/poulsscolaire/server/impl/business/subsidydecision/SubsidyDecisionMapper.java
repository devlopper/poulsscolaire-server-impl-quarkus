package org.cyk.system.poulsscolaire.server.impl.business.subsidydecision;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.registration.SubsidyDecisionDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SubsidyDecision;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link SubsidyDecision} et
 * {@link SubsidyDecisionDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface SubsidyDecisionMapper
    extends IdentifiableMapper<SubsidyDecision, SubsidyDecisionDto> {

}
