package org.cyk.system.poulsscolaire.server.impl.business.deadline;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlinePersistence;

/**
 * Cette class représente un validateur de {@link Deadline}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineValidator
    extends AbstractIdentifiableCodableNamableValidator<Deadline> {

  @Inject
  @Getter
  private DeadlinePersistence persistence;

}
