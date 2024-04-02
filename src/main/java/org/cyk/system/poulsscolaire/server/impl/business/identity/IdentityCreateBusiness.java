package org.cyk.system.poulsscolaire.server.impl.business.identity;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.IdentityService;
import org.cyk.system.poulsscolaire.server.api.IdentityService.IdentityCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
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
    set(identity, array, request);
  }

  /**
   * Cette méthode permet d'assigner les attributs.
   *
   * @param identity identité
   * @param array tableau
   * @param request requête
   */
  public void set(Identity identity, Object[] array, IdentityService.IdentityData request) {
    identity.setFirstName(request.getFirstName());
    identity.setLastNames(request.getLastNames());
    identity.setEmailAddress(request.getEmailAddress());
    identity.setPhoneNumber(request.getPhoneNumber());
    identity.setGender((Gender) array[0]);
  }
}
