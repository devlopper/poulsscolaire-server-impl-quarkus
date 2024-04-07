package org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.DeadlineGroupService.DeadlineGroupCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroup;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroupPersistence;

/**
 * Cette classe représente la création de {@link DeadlineGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineGroupCreateBusiness extends AbstractIdentifiableCreateBusiness<DeadlineGroup,
    DeadlineGroupPersistence, DeadlineGroupValidator, DeadlineGroupCreateRequestDto> {

  @Inject
  @Getter
  DeadlineGroupPersistence persistence;

  @Inject
  @Getter
  DeadlineGroupValidator validator;
}
