package org.cyk.system.poulsscolaire.server.impl.business.period;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Period;
import org.cyk.system.poulsscolaire.server.impl.persistence.PeriodDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.PeriodPersistence;

/**
 * Cette classe représente l'obtention de {@link Period}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class PeriodReadOneBusiness extends AbstractIdentifiableReadOneBusiness<Period,
    PeriodPersistence, PeriodDynamicQuery, PeriodDto, PeriodMapper> {

  protected PeriodReadOneBusiness() {
    super(PeriodDto.class);
  }

  @Inject
  @Getter
  PeriodPersistence persistence;

  @Inject
  @Getter
  PeriodDynamicQuery dynamicQuery;

  @Inject
  @Getter
  PeriodMapper mapper;
}
