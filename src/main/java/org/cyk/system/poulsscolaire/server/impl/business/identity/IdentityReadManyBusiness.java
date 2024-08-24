package org.cyk.system.poulsscolaire.server.impl.business.identity;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityDto;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentityGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityPersistence;

/**
 * Cette classe représente l'obtention de {@link Identity}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityReadManyBusiness
    extends AbstractIdentifiableReadManyBusiness<Identity, IdentityPersistence,
        IdentityDynamicQuery, IdentityDto, IdentityMapper, IdentityGetManyResponseDto> {

  protected IdentityReadManyBusiness() {
    super(IdentityGetManyResponseDto.class);
  }

  @Inject
  @Getter
  IdentityPersistence persistence;

  @Inject
  @Getter
  IdentityDynamicQuery dynamicQuery;

  @Inject
  @Getter
  IdentityMapper mapper;
}
