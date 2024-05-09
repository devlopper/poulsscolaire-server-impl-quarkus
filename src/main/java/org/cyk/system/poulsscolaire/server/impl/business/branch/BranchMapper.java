package org.cyk.system.poulsscolaire.server.impl.business.branch;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Branch} et {@link BranchDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface BranchMapper extends IdentifiableMapper<Branch, BranchDto> {

  Branch map(BranchService.Dto dto);
  
}
