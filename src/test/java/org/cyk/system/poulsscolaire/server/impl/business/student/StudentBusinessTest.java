package org.cyk.system.poulsscolaire.server.impl.business.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.registration.BloodGroup;
import org.cyk.system.poulsscolaire.server.api.registration.StudentDto;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.registration.StudentService.StudentUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;
import org.cyk.system.poulsscolaire.server.impl.persistence.StudentDynamicQuery;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class StudentBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  StudentCreateBusiness studentCreateBusiness;

  @Inject
  StudentReadManyBusiness studentReadManyBusiness;

  @Inject
  StudentReadOneBusiness studentReadOneBusiness;

  @Inject
  StudentReadByIdentifierBusiness studentReadByIdentifierBusiness;

  @Inject
  StudentUpdateBusiness studentUpdateBusiness;

  @Inject
  StudentDeleteBusiness studentDeleteBusiness;

  @Inject
  StudentDynamicQuery studentDynamicQuery;

  DynamicQueryParameters<Student> studentDynamicQueryParameters = new DynamicQueryParameters<>();
  
  @Test
  void student_create() {
    StudentCreateRequestDto request = new StudentCreateRequestDto();
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setGenderIdentifier("M");
    request.setSchoolIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, Student.ENTITY_NAME);
    long identityCount = count(entityManager, Identity.ENTITY_NAME);
    studentCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Student.ENTITY_NAME));
    assertEquals(identityCount + 1, count(entityManager, Identity.ENTITY_NAME));
  }

  @Test
  void student_update() {
    StudentUpdateRequestDto request = new StudentUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setSchoolIdentifier("1");
    request.setEmailAddress("m@m.com");
    request.setGenderIdentifier("M");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    studentUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, Identity.ENTITY_NAME));
  }

  @Test
  void student_delete() {
    DeleteOneRequestDto request = new DeleteOneRequestDto();
    request.setIdentifier("todelete");
    request.setAuditWho("christian");
    long count = count(entityManager, Student.ENTITY_NAME);
    long identityCount = count(entityManager, Identity.ENTITY_NAME);
    studentDeleteBusiness.process(request);
    assertEquals(count - 1, count(entityManager, Student.ENTITY_NAME));
    assertEquals(identityCount - 1, count(entityManager, Identity.ENTITY_NAME));
  }

  @Test
  void student_getOne_asString() {
    studentDynamicQueryParameters.setResultMode(ResultMode.ONE);
    studentDynamicQueryParameters.projection().addNames(StudentDto.JSON_AS_STRING);
    studentDynamicQueryParameters.filter().addCriteria(StudentDto.JSON_IDENTIFIER, "1");
    Student student = studentDynamicQuery.getOne(studentDynamicQueryParameters);
    assertEquals("1 - 1 1", student.asString);
  }
  
  @Test
  void student_getOne_bloodGroup() {
    studentDynamicQueryParameters.setResultMode(ResultMode.ONE);
    studentDynamicQueryParameters.projection().addNames(StudentDto.JSON_BLOOD_GROUP);
    studentDynamicQueryParameters.filter().addCriteria(StudentDto.JSON_IDENTIFIER, "1");
    Student student = studentDynamicQuery.getOne(studentDynamicQueryParameters);
    assertEquals(BloodGroup.A_PLUS, student.bloodGroup);
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/studentbusiness.sql");
    }
  }
}
