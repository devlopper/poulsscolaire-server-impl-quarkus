package org.cyk.system.poulsscolaire.server.impl.business.identity;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.IdentityService.IdentityUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderValidator;
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

  @Inject
  GenderValidator genderValidator;

  @Override
  protected void validate(IdentityUpdateRequestDto request, StringList messages,
      Identity identity) {
    super.validate(request, messages, identity);
    validator.validateFirstName(request.getFirstName(), messages);
    validator.validateLastNames(request.getLastNames(), messages);
    Optional.ofNullable(Core.getOrNullifyIfStringBlank(request.getEmailAddress()))
        .ifPresent(emailAddress -> validator.validateEmailAddress(emailAddress, messages));
    identity.setGender(
        genderValidator.validateInstanceByIdentifier(request.getGenderIdentifier(), messages));
  }

  @Override
  protected void prepare(Identity identity, IdentityUpdateRequestDto request) {
    super.prepare(identity, request);
    identity.setFirstName(request.getFirstName());
    identity.setLastNames(request.getLastNames());
    identity.setEmailAddress(request.getEmailAddress());
    identity.setPhoneNumber(request.getPhoneNumber());
  }
}
