package org.cyk.system.poulsscolaire.server.impl.business.assignmenttype;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.AssignmentTypeService.AssignmentTypeUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentTypePersistence;

/**
 * Cette classe représente la mise à jour de {@link AssignmentType}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AssignmentTypeUpdateBusiness extends AbstractIdentifiableUpdateBusiness<AssignmentType,
    AssignmentTypePersistence, AssignmentTypeValidator, AssignmentTypeUpdateRequestDto> {

  @Inject
  @Getter
  AssignmentTypePersistence persistence;

  @Inject
  @Getter
  AssignmentTypeValidator validator;
}
