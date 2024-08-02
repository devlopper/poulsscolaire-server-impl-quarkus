package org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineGroupDto;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineGroupService.DeadlineGroupGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroup;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroupDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroupPersistence;

/**
 * Cette classe représente l'obtention de {@link DeadlineGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineGroupReadManyBusiness extends
    AbstractIdentifiableReadManyBusiness<DeadlineGroup, DeadlineGroupPersistence,
        DeadlineGroupDynamicQuery, DeadlineGroupDto, DeadlineGroupMapper,
        DeadlineGroupGetManyResponseDto> {

  protected DeadlineGroupReadManyBusiness() {
    super(DeadlineGroupGetManyResponseDto.class);
  }

  @Inject
  @Getter
  DeadlineGroupPersistence persistence;

  @Inject
  @Getter
  DeadlineGroupDynamicQuery dynamicQuery;

  @Inject
  @Getter
  DeadlineGroupMapper mapper;
}
