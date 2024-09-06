package org.cyk.system.poulsscolaire.server.impl.business.branchinstance;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadOneBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstance;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstanceDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstancePersistence;

/**
 * Cette classe représente l'obtention de {@link BranchInstance}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class BranchInstanceReadOneBusiness
    extends AbstractIdentifiableReadOneBusiness<BranchInstance, BranchInstancePersistence,
        BranchInstanceDynamicQuery, BranchInstanceDto, BranchInstanceMapper> {

  protected BranchInstanceReadOneBusiness() {
    super(BranchInstanceDto.class);
  }

  @Inject
  @Getter
  BranchInstancePersistence persistence;

  @Inject
  @Getter
  BranchInstanceDynamicQuery dynamicQuery;

  @Inject
  @Getter
  BranchInstanceMapper mapper;
}
