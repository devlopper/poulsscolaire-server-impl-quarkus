package org.cyk.system.poulsscolaire.server.impl.business.gender;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
import org.cyk.system.poulsscolaire.server.impl.persistence.GenderPersistence;

/**
 * Cette class représente un validateur de {@link Gender}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class GenderValidator extends AbstractIdentifiableCodableNamableValidator<Gender> {

  @Inject
  @Getter
  private GenderPersistence persistence;

}
