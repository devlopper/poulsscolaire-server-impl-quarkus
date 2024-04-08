package org.cyk.system.poulsscolaire.server.impl.business.student;

import ci.gouv.dgbf.extension.server.business.IdentifiableMapper;
import org.cyk.system.poulsscolaire.server.api.registration.StudentDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;
import org.mapstruct.Mapper;

/**
 * Cette interface représente le mapping entre {@link Student} et {@link StudentDto}.
 *
 * @author Christian
 *
 */
@Mapper
public interface StudentMapper extends IdentifiableMapper<Student, StudentDto> {

}
