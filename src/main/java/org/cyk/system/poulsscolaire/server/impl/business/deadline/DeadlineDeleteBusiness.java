package org.cyk.system.poulsscolaire.server.impl.business.deadline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlinePersistence;

/**
 * Cette classe représente la suppression de {@link Deadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineDeleteBusiness extends AbstractIdentifiableDeleteBusiness<Deadline,
    DeadlinePersistence, DeadlineValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  DeadlinePersistence persistence;

  @Inject
  @Getter
  DeadlineValidator validator;
}
