package org.cyk.system.poulsscolaire.server.impl.business.duegroup;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.DueGroupDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.DueGroup;
import org.cyk.system.poulsscolaire.server.impl.persistence.DueGroupDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.DueGroupPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link DueGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DueGroupReadByIdentifierBusiness extends AbstractIdentifiableReadByIdentifierBusiness<
    DueGroup, DueGroupPersistence, DueGroupDynamicQuery, DueGroupDto, DueGroupMapper> {

  protected DueGroupReadByIdentifierBusiness() {
    super(DueGroupDto.class);
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
