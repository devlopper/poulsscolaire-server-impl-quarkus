package org.cyk.system.poulsscolaire.server.impl.business.registration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationDto;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationService.RegistrationGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.RegistrationDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.RegistrationPersistence;

/**
 * Cette classe représente l'obtention de {@link Registration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class RegistrationReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<Registration, RegistrationPersistence,
        RegistrationDynamicQuery, RegistrationDto, RegistrationMapper,
        RegistrationGetManyResponseDto> {

  protected RegistrationReadManyBusiness() {
    super(RegistrationGetManyResponseDto.class);
  }

  @Inject
  @Getter
  RegistrationPersistence persistence;

  @Inject
  @Getter
  RegistrationDynamicQuery dynamicQuery;

  @Inject
  @Getter
  RegistrationMapper mapper;
}
