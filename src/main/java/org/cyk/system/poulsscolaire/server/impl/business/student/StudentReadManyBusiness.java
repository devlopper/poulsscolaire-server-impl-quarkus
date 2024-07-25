package org.cyk.system.poulsscolaire.server.impl.business.student;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableReadManyBusiness;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.api.registration.StudentDto;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentGetManyResponseDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;
import org.cyk.system.poulsscolaire.server.impl.persistence.StudentDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.StudentPersistence;

/**
 * Cette classe représente l'obtention de {@link Student}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StudentReadManyBusiness extends AbstractIdentifiableReadManyBusiness<Student,
    StudentPersistence, StudentDynamicQuery, StudentDto, StudentMapper, StudentGetManyResponseDto> {

  protected StudentReadManyBusiness() {
    super(StudentGetManyResponseDto.class);
  }

  @Inject
  @Getter
  StudentPersistence persistence;

  @Inject
  @Getter
  StudentDynamicQuery dynamicQuery;

  @Inject
  @Getter
  StudentMapper mapper;
}
