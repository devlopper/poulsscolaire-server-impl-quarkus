package org.cyk.system.poulsscolaire.server.impl.business.registration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.RegistrationPersistence;

/**
 * Cette classe représente la suppression de {@link Registration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class RegistrationDeleteBusiness extends AbstractIdentifiableDeleteBusiness<Registration,
    RegistrationPersistence, RegistrationValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  RegistrationPersistence persistence;

  @Inject
  @Getter
  RegistrationValidator validator;
}
