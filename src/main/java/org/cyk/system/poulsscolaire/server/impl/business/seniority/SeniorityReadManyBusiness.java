package org.cyk.system.poulsscolaire.server.impl.business.seniority;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityService.SeniorityGetManyResponseDto;
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
public class SeniorityReadManyBusiness
    extends AbstractIdentifiableReadManyBusiness<Seniority, SeniorityPersistence,
        SeniorityDynamicQuery, SeniorityDto, SeniorityMapper, SeniorityGetManyResponseDto> {

  protected SeniorityReadManyBusiness() {
    super(SeniorityGetManyResponseDto.class);
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
