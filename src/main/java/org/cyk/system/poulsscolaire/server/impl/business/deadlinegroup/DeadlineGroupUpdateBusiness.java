package org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineGroupService.DeadlineGroupUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroup;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroupPersistence;

/**
 * Cette classe représente la mise à jour de {@link DeadlineGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineGroupUpdateBusiness extends AbstractIdentifiableUpdateBusiness<DeadlineGroup,
    DeadlineGroupPersistence, DeadlineGroupValidator, DeadlineGroupUpdateRequestDto> {

  @Inject
  @Getter
  DeadlineGroupPersistence persistence;

  @Inject
  @Getter
  DeadlineGroupValidator validator;
}
