package org.cyk.system.poulsscolaire.server.impl.business.branch;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchPersistence;

/**
 * Cette classe représente la suppression de {@link Branch}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    Branch, BranchPersistence, BranchValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  BranchPersistence persistence;

  @Inject
  @Getter
  BranchValidator validator;
}
