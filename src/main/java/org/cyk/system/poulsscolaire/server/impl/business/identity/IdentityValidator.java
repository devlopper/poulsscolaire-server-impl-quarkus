package org.cyk.system.poulsscolaire.server.impl.business.identity;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityService;
import org.cyk.system.poulsscolaire.server.impl.business.gender.GenderValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityPersistence;

/**
 * Cette class représente un validateur de {@link Identity}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class IdentityValidator extends AbstractIdentifiableValidator<Identity> {

  @Inject
  @Getter
  private IdentityPersistence persistence;

  @Inject
  GenderValidator genderValidator;
  
  public void validateFirstName(String firstName, StringList messages) {
    validationHelper.validateBlankByName(this, firstName, "nom", messages);
  }
  
  public void validateLastNames(String lastNames, StringList messages) {
    validationHelper.validateBlankByName(this, lastNames, "prénoms", messages);
  }
  
  public void validateEmailAddress(String emailAddress, StringList messages) {
    validationHelper.validateEmailAddressByName(this, emailAddress, "adresse email", messages);
  }
  
  /**
   * Cette méthode permet de valider.
   *
   * @param request requête
   * @param messages messages
   * @return tableau
   */
  public Object[] validate(IdentityService.IdentityData request, StringList messages) {
    validateFirstName(request.getFirstName(), messages);
    validateLastNames(request.getLastNames(), messages);
    Optional.ofNullable(Core.getOrNullifyIfStringBlank(request.getEmailAddress()))
        .ifPresent(value -> validateEmailAddress(value, messages));
    Gender gender =
        genderValidator.validateInstanceByIdentifier(request.getGenderIdentifier(), messages);
    return new Object[] {gender};
  }
}
