package org.cyk.system.poulsscolaire.server.impl.business.assignmenttype;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.AssignmentTypeDto;
import org.cyk.system.poulsscolaire.server.api.configuration.AssignmentTypeService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentTypeDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentTypePersistence;

/**
 * Cette classe représente l'obtention de {@link AssignmentType}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AssignmentTypeReadManyBusiness
    extends AbstractIdentifiableReadManyBusiness<AssignmentType, AssignmentTypePersistence,
        AssignmentTypeDynamicQuery, AssignmentTypeDto, AssignmentTypeMapper, GetManyResponseDto> {

  protected AssignmentTypeReadManyBusiness() {
    super(GetManyResponseDto.class);
  }

  @Inject
  @Getter
  AssignmentTypePersistence persistence;

  @Inject
  @Getter
  AssignmentTypeDynamicQuery dynamicQuery;

  @Inject
  @Getter
  AssignmentTypeMapper mapper;
}
