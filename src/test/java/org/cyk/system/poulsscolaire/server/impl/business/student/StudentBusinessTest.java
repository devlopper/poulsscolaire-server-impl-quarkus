package org.cyk.system.poulsscolaire.server.impl.business.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.StudentService.StudentCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Student;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(StudentBusinessTest.Profile.class)
class StudentBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  StudentCreateBusiness createBusiness;

  @Inject
  StudentReadManyBusiness readManyBusiness;
  
  @Inject
  StudentReadOneBusiness readOneBusiness;
  
  @Inject
  StudentReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  StudentUpdateBusiness updateBusiness;
  
  @Inject
  StudentDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    StudentCreateRequestDto request = new StudentCreateRequestDto();
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setGenderIdentifier("M");
    request.setAuditWho("christian");
    long count = count(entityManager, Student.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Student.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/studentbusiness.sql");
    }
  }
}
