package org.cyk.system.poulsscolaire.server.impl.business.assignmenttype;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.AssignmentTypeService.AssignmentTypeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentTypePersistence;

/**
 * Cette classe représente la création de {@link AssignmentType}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AssignmentTypeCreateBusiness extends AbstractIdentifiableCreateBusiness<AssignmentType,
    AssignmentTypePersistence, AssignmentTypeValidator, AssignmentTypeCreateRequestDto> {

  @Inject
  @Getter
  AssignmentTypePersistence persistence;

  @Inject
  @Getter
  AssignmentTypeValidator validator;
}
