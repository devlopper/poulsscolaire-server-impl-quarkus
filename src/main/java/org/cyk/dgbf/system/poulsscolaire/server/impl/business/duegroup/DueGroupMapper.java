package org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.dgbf.system.poulsscolaire.server.impl.persistence.DueGroup;
import org.cyk.system.poulsscolaire.server.api.DueGroupDto;
import org.mapstruct.Mapper;

/**
 * Cette interface représente les fonctionnlités de mapping entre l'objet de persistence et l'objet
 * de tranfert de données.
 *
 * @author Christian
 *
 */
@Mapper
public interface DueGroupMapper extends IdentifiableMapper<DueGroup, DueGroupDto> {

}
