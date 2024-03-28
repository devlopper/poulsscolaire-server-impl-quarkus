package org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.dgbf.system.poulsscolaire.server.impl.persistence.DueGroup;
import org.cyk.dgbf.system.poulsscolaire.server.impl.persistence.DueGroupPersistence;
import org.cyk.system.poulsscolaire.server.api.DueGroupService.DueGroupCreateRequestDto;

/**
 * Cette classe représente la création d'un {@link DueGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DueGroupCreateBusiness extends AbstractIdentifiableCreateBusiness<DueGroup,
    DueGroupPersistence, DueGroupValidator, DueGroupCreateRequestDto> {

  @Inject
  @Getter
  DueGroupPersistence persistence;

  @Inject
  @Getter
  DueGroupValidator validator;
}
