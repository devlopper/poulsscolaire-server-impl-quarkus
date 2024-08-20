package org.cyk.system.poulsscolaire.server.impl.business.student;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCodableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentCreateParentRequestDto.ParentalLink;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;
import org.cyk.system.poulsscolaire.server.impl.persistence.StudentPersistence;

/**
 * Cette class représente un validateur de {@link Student}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StudentValidator extends AbstractIdentifiableCodableValidator<Student> {

  @Inject
  @Getter
  private StudentPersistence persistence;

  /**
   * Cette méthode permet de valider l'existence d'un parent.
   *
   * @param parentalLink {@link ParentalLink}
   * @param identity {@link IdentityDto}
   * @param messages messages
   */
  public boolean validateParentExistence(Identity identity, ParentalLink parentalLink,
      StringList messages) {
    if (ParentalLink.FATHER.equals(parentalLink)) {
      return messages.addIfNotNull(identity, "Le père existe déja");
    }
    if (ParentalLink.MOTHER.equals(parentalLink)) {
      return messages.addIfNotNull(identity, "Le mère existe déja");
    }
    if (ParentalLink.TUTOR.equals(parentalLink)) {
      return messages.addIfNotNull(identity, "Le tuteur existe déja");
    }
    return false;
  }
}
