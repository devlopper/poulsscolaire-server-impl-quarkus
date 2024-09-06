package org.cyk.system.poulsscolaire.server.impl.business.branchinstance;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceService.BranchInstanceGetManyResponseDto;
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
public class BranchInstanceReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<BranchInstance, BranchInstancePersistence,
        BranchInstanceDynamicQuery, BranchInstanceDto, BranchInstanceMapper,
        BranchInstanceGetManyResponseDto> {

  protected BranchInstanceReadManyBusiness() {
    super(BranchInstanceGetManyResponseDto.class);
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
