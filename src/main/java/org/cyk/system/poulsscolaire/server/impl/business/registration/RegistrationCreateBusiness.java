package org.cyk.system.poulsscolaire.server.impl.business.registration;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableCreateBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.RegistrationService.RegistrationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.assignmenttype.AssignmentTypeValidator;
import org.cyk.system.poulsscolaire.server.impl.business.schooling.SchoolingValidator;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityValidator;
import org.cyk.system.poulsscolaire.server.impl.business.student.StudentValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
import org.cyk.system.poulsscolaire.server.impl.persistence.Registration;
import org.cyk.system.poulsscolaire.server.impl.persistence.RegistrationPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.cyk.system.poulsscolaire.server.impl.persistence.Seniority;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;

/**
 * Cette classe représente la création de {@link Registration}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class RegistrationCreateBusiness extends AbstractIdentifiableCreateBusiness<Registration,
    RegistrationPersistence, RegistrationValidator, RegistrationCreateRequestDto> {

  @Inject
  @Getter
  RegistrationPersistence persistence;

  @Inject
  @Getter
  RegistrationValidator validator;

  @Inject
  StudentValidator studentValidator;

  @Inject
  SchoolingValidator schoolingValidator;

  @Inject
  AssignmentTypeValidator assignmentTypeValidator;

  @Inject
  SeniorityValidator seniorityValidator;

  @Override
  protected Object[] validate(RegistrationCreateRequestDto request, StringList messages) {
    Student student =
        studentValidator.validateInstanceByIdentifier(request.getStudentIdentifier(), messages);
    Schooling schooling =
        schoolingValidator.validateInstanceByIdentifier(request.getSchoolingIdentifier(), messages);
    AssignmentType assignmentType = assignmentTypeValidator
        .validateInstanceByIdentifier(request.getAssignmnetTypeIdentifier(), messages);
    Seniority seniority = seniorityValidator
        .validateInstanceByIdentifier(request.getSeniorityIdentifier(), messages);
    return new Object[] {student, schooling, assignmentType, seniority};
  }

  @Override
  protected void setFields(Registration registration, Object[] array,
      RegistrationCreateRequestDto request) {
    super.setFields(registration, array, request);
    registration.student = (Student) array[0];
    registration.schooling = (Schooling) array[1];
    registration.assignmentType = (AssignmentType) array[2];
    registration.seniority = (Seniority) array[3];
    registration.setCode(String.format("I%s", System.currentTimeMillis()));
  }
}
