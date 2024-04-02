package org.cyk.system.poulsscolaire.server.impl.business.registration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.RegistrationService.RegistrationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.RegistrationPersistence;

/**
 * Cette classe représente la création de {@link Registration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class RegistrationCreateBusiness extends AbstractIdentifiableCreateBusiness<Registration,
    RegistrationPersistence, RegistrationValidator, RegistrationCreateRequestDto> {

  @Inject
  @Getter
  RegistrationPersistence persistence;

  @Inject
  @Getter
  RegistrationValidator validator;
}
