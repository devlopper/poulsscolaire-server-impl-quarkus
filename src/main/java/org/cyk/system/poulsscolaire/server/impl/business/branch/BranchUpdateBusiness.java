package org.cyk.system.poulsscolaire.server.impl.business.branch;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.BranchService.BranchUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchPersistence;

/**
 * Cette classe représente la mise à jour de {@link Branch}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchUpdateBusiness extends AbstractIdentifiableUpdateBusiness<Branch,
    BranchPersistence, BranchValidator, BranchUpdateRequestDto> {

  @Inject
  @Getter
  BranchPersistence persistence;

  @Inject
  @Getter
  BranchValidator validator;
}
