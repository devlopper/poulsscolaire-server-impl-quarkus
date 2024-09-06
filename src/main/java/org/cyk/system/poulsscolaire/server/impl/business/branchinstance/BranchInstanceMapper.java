package org.cyk.system.poulsscolaire.server.impl.business.branchinstance;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstance;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link BranchInstance} et {@link BranchInstanceDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface BranchInstanceMapper
    extends IdentifiableMapper<BranchInstance, BranchInstanceDto> {

}
