package org.cyk.system.poulsscolaire.server.impl.business.schooling;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolingPersistence;

/**
 * Cette class représente un validateur de {@link Schooling}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class SchoolingValidator extends AbstractIdentifiableValidator<Schooling> {

  @Inject
  @Getter
  private SchoolingPersistence persistence;

}
