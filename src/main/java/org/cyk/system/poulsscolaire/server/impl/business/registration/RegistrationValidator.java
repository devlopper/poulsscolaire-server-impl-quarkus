package org.cyk.system.poulsscolaire.server.impl.business.registration;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.RegistrationPersistence;

/**
 * Cette class représente un validateur de {@link Registration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class RegistrationValidator
    extends AbstractIdentifiableCodableNamableValidator<Registration> {

  @Inject
  @Getter
  private RegistrationPersistence persistence;

}
