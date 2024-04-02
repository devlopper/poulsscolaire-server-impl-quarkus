package org.cyk.system.poulsscolaire.server.impl.business.assignmenttype;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.AssignmentTypeDto;
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
public class AssignmentTypeReadOneBusiness
    extends AbstractIdentifiableReadOneBusiness<AssignmentType, AssignmentTypePersistence,
        AssignmentTypeDynamicQuery, AssignmentTypeDto, AssignmentTypeMapper> {

  protected AssignmentTypeReadOneBusiness() {
    super(AssignmentTypeDto.class);
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
