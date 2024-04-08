package org.cyk.system.poulsscolaire.server.impl.business.identity;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadByIdentifierBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityPersistence;

/**
 * Cette classe représente l'obtention par identifiant de {@link Identity}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityReadByIdentifierBusiness extends AbstractIdentifiableReadByIdentifierBusiness<
    Identity, IdentityPersistence, IdentityDynamicQuery, IdentityDto, IdentityMapper> {

  protected IdentityReadByIdentifierBusiness() {
    super(IdentityDto.class);
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
