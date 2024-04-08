package org.cyk.system.poulsscolaire.server.impl.business.gender;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.GenderDto;
import org.cyk.system.poulsscolaire.server.api.configuration.GenderService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
import org.cyk.system.poulsscolaire.server.impl.persistence.GenderDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.GenderPersistence;

/**
 * Cette classe représente l'obtention de {@link Gender}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class GenderReadManyBusiness extends AbstractIdentifiableReadManyBusiness<Gender,
    GenderPersistence, GenderDynamicQuery, GenderDto, GenderMapper, GetManyResponseDto> {

  protected GenderReadManyBusiness() {
    super(GetManyResponseDto.class);
  }

  @Inject
  @Getter
  GenderPersistence persistence;

  @Inject
  @Getter
  GenderDynamicQuery dynamicQuery;

  @Inject
  @Getter
  GenderMapper mapper;
}
