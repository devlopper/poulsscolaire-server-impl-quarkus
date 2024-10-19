package org.cyk.system.poulsscolaire.server.impl.business.schoolconfiguration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfiguration;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfigurationPersistence;

/**
 * Cette classe représente la suppression de {@link SchoolConfiguration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolConfigurationDeleteBusiness
    extends AbstractIdentifiableDeleteBusiness<SchoolConfiguration, SchoolConfigurationPersistence,
        SchoolConfigurationValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  SchoolConfigurationPersistence persistence;

  @Inject
  @Getter
  SchoolConfigurationValidator validator;
}
