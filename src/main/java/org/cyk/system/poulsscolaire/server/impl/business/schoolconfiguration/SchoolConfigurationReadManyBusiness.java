package org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationService.SchoolConfigurationGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfiguration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfigurationDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfigurationPersistence;

/**
 * Cette classe représente l'obtention de {@link SchoolConfiguration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolConfigurationReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<SchoolConfiguration, SchoolConfigurationPersistence,
        SchoolConfigurationDynamicQuery, SchoolConfigurationDto, SchoolConfigurationMapper,
        SchoolConfigurationGetManyResponseDto> {

  protected SchoolConfigurationReadManyBusiness() {
    super(SchoolConfigurationGetManyResponseDto.class);
  }

  @Inject
  @Getter
  SchoolConfigurationPersistence persistence;

  @Inject
  @Getter
  SchoolConfigurationDynamicQuery dynamicQuery;

  @Inject
  @Getter
  SchoolConfigurationMapper mapper;
}
