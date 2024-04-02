package org.cyk.system.poulsscolaire.server.impl.business.assignmenttype;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentTypePersistence;

/**
 * Cette class représente un validateur de {@link AssignmentType}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AssignmentTypeValidator
    extends AbstractIdentifiableCodableNamableValidator<AssignmentType> {

  @Inject
  @Getter
  private AssignmentTypePersistence persistence;

}
