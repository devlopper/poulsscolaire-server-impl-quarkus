package org.cyk.system.poulsscolaire.server.impl.business.branch;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableNamableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchPersistence;

/**
 * Cette class représente un validateur de {@link Branch}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchValidator extends AbstractIdentifiableCodableNamableValidator<Branch> {

  @Inject
  @Getter
  private BranchPersistence persistence;

}
