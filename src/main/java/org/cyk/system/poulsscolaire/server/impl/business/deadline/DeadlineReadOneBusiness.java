package org.cyk.system.poulsscolaire.server.impl.business.deadline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlinePersistence;

/**
 * Cette classe représente l'obtention de {@link Deadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineReadOneBusiness extends AbstractIdentifiableReadOneBusiness<Deadline,
    DeadlinePersistence, DeadlineDynamicQuery, DeadlineDto, DeadlineMapper> {

  protected DeadlineReadOneBusiness() {
    super(DeadlineDto.class);
  }

  @Inject
  @Getter
  DeadlinePersistence persistence;

  @Inject
  @Getter
  DeadlineDynamicQuery dynamicQuery;

  @Inject
  @Getter
  DeadlineMapper mapper;
}
