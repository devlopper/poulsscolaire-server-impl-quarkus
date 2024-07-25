package org.cyk.system.poulsscolaire.server.impl.business.schooling;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolingDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolingPersistence;

/**
 * Cette classe représente l'obtention de {@link Schooling}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolingReadManyBusiness
    extends AbstractIdentifiableReadManyBusiness<Schooling, SchoolingPersistence,
        SchoolingDynamicQuery, SchoolingDto, SchoolingMapper, SchoolingGetManyResponseDto> {

  protected SchoolingReadManyBusiness() {
    super(SchoolingGetManyResponseDto.class);
  }

  @Inject
  @Getter
  SchoolingPersistence persistence;

  @Inject
  @Getter
  SchoolingDynamicQuery dynamicQuery;

  @Inject
  @Getter
  SchoolingMapper mapper;
}
