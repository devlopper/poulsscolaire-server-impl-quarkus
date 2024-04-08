package org.cyk.system.poulsscolaire.server.impl.business.seniority;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Seniority;
import org.cyk.system.poulsscolaire.server.impl.persistence.SeniorityDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SeniorityPersistence;

/**
 * Cette classe représente l'obtention de {@link Seniority}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SeniorityReadOneBusiness extends AbstractIdentifiableReadOneBusiness<Seniority,
    SeniorityPersistence, SeniorityDynamicQuery, SeniorityDto, SeniorityMapper> {

  protected SeniorityReadOneBusiness() {
    super(SeniorityDto.class);
  }

  @Inject
  @Getter
  SeniorityPersistence persistence;

  @Inject
  @Getter
  SeniorityDynamicQuery dynamicQuery;

  @Inject
  @Getter
  SeniorityMapper mapper;
}
