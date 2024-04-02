package org.cyk.system.poulsscolaire.server.impl.business.duegroup;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.DueGroup;
import org.cyk.system.poulsscolaire.server.impl.persistence.DueGroupPersistence;

/**
 * Cette class représente un validateur de {@link DueGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DueGroupValidator extends AbstractIdentifiableCodableNamableValidator<DueGroup> {

  @Inject
  @Getter
  private DueGroupPersistence persistence;

}
