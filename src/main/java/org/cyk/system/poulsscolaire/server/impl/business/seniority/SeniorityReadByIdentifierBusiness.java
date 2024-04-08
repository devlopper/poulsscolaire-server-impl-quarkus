package org.cyk.system.poulsscolaire.server.impl.business.seniority;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Seniority;
import org.cyk.system.poulsscolaire.server.impl.persistence.SeniorityDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SeniorityPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link Seniority}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SeniorityReadByIdentifierBusiness extends AbstractIdentifiableReadByIdentifierBusiness<
    Seniority, SeniorityPersistence, SeniorityDynamicQuery, SeniorityDto, SeniorityMapper> {

  protected SeniorityReadByIdentifierBusiness() {
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
