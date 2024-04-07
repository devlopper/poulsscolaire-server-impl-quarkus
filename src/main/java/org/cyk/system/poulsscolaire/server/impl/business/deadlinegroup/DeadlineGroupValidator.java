package org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroup;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroupPersistence;

/**
 * Cette class représente un validateur de {@link DeadlineGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineGroupValidator
    extends AbstractIdentifiableCodableNamableValidator<DeadlineGroup> {

  @Inject
  @Getter
  private DeadlineGroupPersistence persistence;

}
