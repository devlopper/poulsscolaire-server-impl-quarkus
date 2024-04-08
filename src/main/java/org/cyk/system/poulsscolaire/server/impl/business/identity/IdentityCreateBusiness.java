package org.cyk.system.poulsscolaire.server.impl.business.identity;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentityCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityPersistence;

/**
 * Cette classe représente la création de {@link Identity}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityCreateBusiness extends AbstractIdentifiableCreateBusiness<Identity,
    IdentityPersistence, IdentityValidator, IdentityCreateRequestDto> {

  @Inject
  @Getter
  IdentityPersistence persistence;

  @Inject
  @Getter
  IdentityValidator validator;

  @Override
  protected Object[] validate(IdentityCreateRequestDto request, StringList messages) {
    return validator.validate(request, messages);
  }

  @Override
  protected void setFields(Identity identity, Object[] array, IdentityCreateRequestDto request) {
    super.setFields(identity, array, request);
    identity.set(request, array);
  }
}
