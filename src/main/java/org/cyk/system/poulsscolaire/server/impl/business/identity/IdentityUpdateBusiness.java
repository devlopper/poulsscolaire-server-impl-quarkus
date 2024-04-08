package org.cyk.system.poulsscolaire.server.impl.business.identity;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService.IdentityUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityPersistence;

/**
 * Cette classe représente la mise à jour de {@link Identity}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityUpdateBusiness extends AbstractIdentifiableUpdateBusiness<Identity,
    IdentityPersistence, IdentityValidator, IdentityUpdateRequestDto> {

  @Inject
  @Getter
  IdentityPersistence persistence;

  @Inject
  @Getter
  IdentityValidator validator;

  @Override
  protected void validate(IdentityUpdateRequestDto request, StringList messages,
      Identity identity) {
    super.validate(request, messages, identity);
    Object[] array = validator.validate(request, messages);
    identity.gender = (Gender) array[0];
  }

  @Override
  protected void prepare(Identity identity, IdentityUpdateRequestDto request) {
    super.prepare(identity, request);
    identity.set(request, null);
  }
}
