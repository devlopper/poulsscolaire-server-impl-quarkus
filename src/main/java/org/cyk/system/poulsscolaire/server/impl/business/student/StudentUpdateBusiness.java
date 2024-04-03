package org.cyk.system.poulsscolaire.server.impl.business.student;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableUpdateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.StudentService.StudentUpdateRequestDto;
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

  @Override
  protected void prepare(Student student, StudentUpdateRequestDto request) {
    super.prepare(student, request);
    student.registrationNumber = request.getRegistrationNumber();
  }
}
