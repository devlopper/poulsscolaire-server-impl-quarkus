package org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolConfigurationDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfiguration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfigurationDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfigurationPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link SchoolConfiguration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolConfigurationReadByIdentifierBusiness extends
    AbstractIdentifiableReadByIdentifierBusiness<SchoolConfiguration,
        SchoolConfigurationPersistence, SchoolConfigurationDynamicQuery, SchoolConfigurationDto,
        SchoolConfigurationMapper> {

  protected SchoolConfigurationReadByIdentifierBusiness() {
    super(SchoolConfigurationDto.class);
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
