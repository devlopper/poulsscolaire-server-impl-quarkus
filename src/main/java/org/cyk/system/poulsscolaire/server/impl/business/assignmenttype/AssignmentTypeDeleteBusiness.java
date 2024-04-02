package org.cyk.system.poulsscolaire.server.impl.business.assignmenttype;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentTypePersistence;

/**
 * Cette classe représente la suppression de {@link AssignmentType}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AssignmentTypeDeleteBusiness extends AbstractIdentifiableDeleteBusiness<AssignmentType,
    AssignmentTypePersistence, AssignmentTypeValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  AssignmentTypePersistence persistence;

  @Inject
  @Getter
  AssignmentTypeValidator validator;
}
