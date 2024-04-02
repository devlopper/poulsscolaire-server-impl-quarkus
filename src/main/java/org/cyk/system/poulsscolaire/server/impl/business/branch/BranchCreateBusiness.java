package org.cyk.system.poulsscolaire.server.impl.business.branch;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.BranchService.BranchCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchPersistence;

/**
 * Cette classe représente la création de {@link Branch}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchCreateBusiness extends AbstractIdentifiableCreateBusiness<Branch,
    BranchPersistence, BranchValidator, BranchCreateRequestDto> {

  @Inject
  @Getter
  BranchPersistence persistence;

  @Inject
  @Getter
  BranchValidator validator;
}
