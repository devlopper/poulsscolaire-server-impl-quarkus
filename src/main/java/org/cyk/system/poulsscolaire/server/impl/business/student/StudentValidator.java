package org.cyk.system.poulsscolaire.server.impl.business.student;

import ci.gouv.dgbf.extension.server.business.AbstractIdentifiableValidator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.Getter;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;
import org.cyk.system.poulsscolaire.server.impl.persistence.StudentPersistence;

/**
 * Cette class représente un validateur de {@link Student}.
 *
 * @author Christian
 *
 */
@ApplicationScoped
public class StudentValidator extends AbstractIdentifiableValidator<Student> {

  @Inject
  @Getter
  private StudentPersistence persistence;

}
