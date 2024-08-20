package org.cyk.system.poulsscolaire.server.impl.business.student;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Objects;
import java.util.Optional;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentCreateParentRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentCreateParentRequestDto.ParentalLink;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;
import org.cyk.system.poulsscolaire.server.impl.persistence.StudentPersistence;

/**
 * Cette classe représente la création de parent de {@link Student}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StudentCreateParentBusiness extends AbstractIdentifiableUpdateBusiness<Student,
    StudentPersistence, StudentValidator, StudentCreateParentRequestDto> {

  @Inject
  @Getter
  StudentPersistence persistence;

  @Inject
  @Getter
  StudentValidator validator;

  @Inject
  IdentityValidator identityValidator;

  @Inject
  IdentityPersistence identityPersistence;

  @Override
  protected void validate(StudentCreateParentRequestDto request, StringList messages,
      Student student) {
    super.validate(request, messages, student);
    boolean isParentalLinkNull =
        messages.addIfNull(request.getLink(), "Le lien parental est requis");
    Core.runIfTrue(!isParentalLinkNull, () -> {
      validator.validateParentExistence(getParent(student, request.getLink()), request.getLink(),
          messages);
    });
  }

  Identity getParent(Student student, ParentalLink parentalLink) {
    if (ParentalLink.FATHER.equals(parentalLink)) {
      return student.fatherIdentity;
    }
    if (ParentalLink.MOTHER.equals(parentalLink)) {
      return student.motherIdentity;
    }
    if (ParentalLink.TUTOR.equals(parentalLink)) {
      return student.tutorIdentity;
    }
    return null;
  }

  @Override
  protected void prepare(Student student, StudentCreateParentRequestDto request) {
    super.prepare(student, request);
    Identity identity = instantiateIdentity(student, request.getLink());
    identity.set(request, null);
  }

  Identity instantiateIdentity(Student student, ParentalLink link) {
    Identity identity = null;
    if (ParentalLink.FATHER.equals(link)) {
      identity = new Identity();
      student.fatherIdentity = identity;
    } else if (ParentalLink.MOTHER.equals(link)) {
      identity = new Identity();
      student.motherIdentity = identity;
    } else if (ParentalLink.TUTOR.equals(link)) {
      identity = new Identity();
      student.tutorIdentity = identity;
    }
    return identity;
  }

  @Override
  protected void doTransact(Student student) {
    Identity identity = getIdentity(student);
    identity.generateIdentifier();
    identity.audit = student.audit;
    identityPersistence.create(identity);
    super.doTransact(student);
  }

  Identity getIdentity(Student student) {
    if (isIdentityIdentifierNull(student.fatherIdentity)) {
      return student.fatherIdentity;
    } else if (isIdentityIdentifierNull(student.motherIdentity)) {
      return student.motherIdentity;
    } else if (isIdentityIdentifierNull(student.tutorIdentity)) {
      return student.tutorIdentity;
    }
    return null;
  }

  boolean isIdentityIdentifierNull(Identity identity) {
    return Optional.ofNullable(identity).map(i -> Objects.isNull(i.identifier)).orElse(false);
  }
}
