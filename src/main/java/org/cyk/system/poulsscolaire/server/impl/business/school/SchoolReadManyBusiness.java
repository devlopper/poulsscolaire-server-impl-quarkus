package org.cyk.system.poulsscolaire.server.impl.business.school;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolService.GetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.School;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolPersistence;

/**
 * Cette classe représente l'obtention de {@link School}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolReadManyBusiness extends AbstractIdentifiableReadManyBusiness<School,
    SchoolPersistence, SchoolDynamicQuery, SchoolDto, SchoolMapper, GetManyResponseDto> {

  protected SchoolReadManyBusiness() {
    super(GetManyResponseDto.class);
  }

  @Inject
  @Getter
  SchoolPersistence persistence;

  @Inject
  @Getter
  SchoolDynamicQuery dynamicQuery;

  @Inject
  @Getter
  SchoolMapper mapper;
}
