package org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineGroupDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroup;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroupDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroupPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link DeadlineGroup}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class DeadlineGroupReadByIdentifierBusiness
    extends AbstractIdentifiableReadByIdentifierBusiness<DeadlineGroup, DeadlineGroupPersistence,
        DeadlineGroupDynamicQuery, DeadlineGroupDto, DeadlineGroupMapper> {

  protected DeadlineGroupReadByIdentifierBusiness() {
    super(DeadlineGroupDto.class);
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
