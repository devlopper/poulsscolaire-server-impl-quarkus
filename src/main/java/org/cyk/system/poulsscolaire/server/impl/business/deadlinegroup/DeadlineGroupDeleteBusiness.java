package org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroup;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroupPersistence;

/**
 * Cette classe représente la suppression de {@link DeadlineGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineGroupDeleteBusiness extends AbstractIdentifiableDeleteBusiness<DeadlineGroup,
    DeadlineGroupPersistence, DeadlineGroupValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  DeadlineGroupPersistence persistence;

  @Inject
  @Getter
  DeadlineGroupValidator validator;
}
