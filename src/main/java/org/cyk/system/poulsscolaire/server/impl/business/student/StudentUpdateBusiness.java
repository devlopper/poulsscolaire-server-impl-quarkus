package org.cyk.system.poulsscolaire.server.impl.business.student;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;
import org.cyk.system.poulsscolaire.server.impl.persistence.StudentPersistence;

/**
 * Cette classe représente la mise à jour de {@link Student}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StudentUpdateBusiness extends AbstractIdentifiableUpdateBusiness<Student,
    StudentPersistence, StudentValidator, StudentUpdateRequestDto> {

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
  protected void validate(StudentUpdateRequestDto request, StringList messages, Student student) {
    super.validate(request, messages, student);
    Object[] array = identityValidator.validate(request, messages);
    student.identity.gender = (Gender) array[0];
  }

  @Override
  protected void prepare(Student student, StudentUpdateRequestDto request) {
    super.prepare(student, request);
    student.identity.set(request, null);
    student.registrationNumber = request.getRegistrationNumber();
  }

  @Override
  protected void doTransact(Student student) {
    super.doTransact(student);
    identityPersistence.update(student.identity);
  }
}
