package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.registration.BloodGroup;
import org.cyk.system.poulsscolaire.server.api.registration.StudentDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(StudentDynamicQueryTest.Profile.class)
class StudentDynamicQueryTest {

  @Inject
  StudentDynamicQuery dynamicQuery;

  DynamicQueryParameters<Student> parameters = new DynamicQueryParameters<>();
  
  @Test
  void getOne_asString() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(StudentDto.JSON_AS_STRING);
    parameters.filter().addCriteria(StudentDto.JSON_IDENTIFIER, "1");
    Student student = dynamicQuery.getOne(parameters);
    assertEquals("1 - 1 1", student.asString);
  }
  
  @Test
  void getOne_bloodGroup() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(StudentDto.JSON_BLOOD_GROUP);
    parameters.filter().addCriteria(StudentDto.JSON_IDENTIFIER, "1");
    Student student = dynamicQuery.getOne(parameters);
    assertEquals(BloodGroup.A_PLUS, student.bloodGroup);
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/studentdynamicquery.sql");
    }
  }
}
