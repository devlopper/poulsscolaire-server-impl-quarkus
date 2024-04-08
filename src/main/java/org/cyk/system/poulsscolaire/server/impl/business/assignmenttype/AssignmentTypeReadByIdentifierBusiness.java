package org.cyk.system.poulsscolaire.server.impl.business.assignmenttype;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.AssignmentTypeDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentTypeDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentTypePersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link AssignmentType}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class AssignmentTypeReadByIdentifierBusiness
    extends AbstractIdentifiableReadByIdentifierBusiness<AssignmentType, AssignmentTypePersistence,
        AssignmentTypeDynamicQuery, AssignmentTypeDto, AssignmentTypeMapper> {

  protected AssignmentTypeReadByIdentifierBusiness() {
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
