package org.cyk.system.poulsscolaire.server.impl.business.student;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableDeleteBusiness;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;
import org.cyk.system.poulsscolaire.server.impl.persistence.StudentPersistence;

/**
 * Cette classe représente la suppression de {@link Student}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StudentDeleteBusiness extends AbstractIdentifiableDeleteBusiness<
    Student, StudentPersistence, StudentValidator, DeleteOneRequestDto> {

  @Inject
  @Getter
  StudentPersistence persistence;

  @Inject
  @Getter
  StudentValidator validator;
}
