package org.cyk.system.poulsscolaire.server.impl.business.student;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.StudentService.StudentCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.identity.IdentityValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.IdentityPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;
import org.cyk.system.poulsscolaire.server.impl.persistence.StudentPersistence;

/**
 * Cette classe représente la création de {@link Student}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StudentCreateBusiness extends AbstractIdentifiableCreateBusiness<Student,
    StudentPersistence, StudentValidator, StudentCreateRequestDto> {

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
  protected Object[] validate(StudentCreateRequestDto request, StringList messages) {
    return identityValidator.validate(request, messages);
  }

  @Override
  protected void setFields(Student student, Object[] array, StudentCreateRequestDto request) {
    super.setFields(student, array, request);
    student.identity = new Identity();
    student.identity.generateIdentifier();
    student.identity.audit = student.audit;
    student.identity.set(request, array);
    student.registrationNumber = request.getRegistrationNumber();
  }

  @Override
  protected void doTransact(Student student) {
    identityPersistence.create(student.identity);
    super.doTransact(student);
  }
}
