package org.cyk.system.poulsscolaire.server.impl.business.duegroup;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.DueGroupService.DueGroupUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.DueGroup;
import org.cyk.system.poulsscolaire.server.impl.persistence.DueGroupPersistence;

/**
 * Cette classe représente la mise à jour de {@link DueGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DueGroupUpdateBusiness extends AbstractIdentifiableUpdateBusiness<DueGroup,
    DueGroupPersistence, DueGroupValidator, DueGroupUpdateRequestDto> {

  @Inject
  @Getter
  DueGroupPersistence persistence;

  @Inject
  @Getter
  DueGroupValidator validator;
}
