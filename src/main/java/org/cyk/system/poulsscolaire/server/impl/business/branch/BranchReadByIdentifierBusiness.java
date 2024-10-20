package org.cyk.system.poulsscolaire.server.impl.business.branch;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link Branch}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchReadByIdentifierBusiness extends AbstractIdentifiableReadByIdentifierBusiness<
    Branch, BranchPersistence, BranchDynamicQuery, BranchDto, BranchMapper> {

  protected BranchReadByIdentifierBusiness() {
    super(BranchDto.class);
  }

  @Inject
  @Getter
  BranchPersistence persistence;

  @Inject
  @Getter
  BranchDynamicQuery dynamicQuery;

  @Inject
  @Getter
  BranchMapper mapper;
}
