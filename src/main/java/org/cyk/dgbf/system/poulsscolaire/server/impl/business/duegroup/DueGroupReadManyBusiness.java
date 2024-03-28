package org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.dgbf.system.poulsscolaire.server.impl.persistence.DueGroup;
import org.cyk.dgbf.system.poulsscolaire.server.impl.persistence.DueGroupDynamicQuery;
import org.cyk.dgbf.system.poulsscolaire.server.impl.persistence.DueGroupPersistence;
import org.cyk.system.poulsscolaire.server.api.DueGroupDto;
import org.cyk.system.poulsscolaire.server.api.DueGroupService.GetManyResponseDto;

/**
 * Cette classe représente l'obtention de {@link DueGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DueGroupReadManyBusiness extends AbstractIdentifiableReadManyBusiness<DueGroup,
    DueGroupPersistence, DueGroupDynamicQuery, DueGroupDto, DueGroupMapper, GetManyResponseDto> {

  protected DueGroupReadManyBusiness() {
    super(GetManyResponseDto.class);
  }

  @Inject
  @Getter
  DueGroupPersistence persistence;

  @Inject
  @Getter
  DueGroupDynamicQuery dynamicQuery;

  @Inject
  @Getter
  DueGroupMapper mapper;
}
