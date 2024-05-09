package org.cyk.system.poulsscolaire.server.impl.business.school;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolService.SchoolRepatriateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.School;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
@TestProfile(SchoolBusinessTest.Profile.class)
class SchoolBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  SchoolReadManyBusiness readManyBusiness;

  @Inject
  SchoolReadOneBusiness readOneBusiness;

  @Inject
  SchoolReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  SchoolRepatriateBusiness repatriateBusiness;

  @Test
  void repatriate() {
    SchoolService.Dto school = new SchoolService.Dto();
    school.setIdentifier(UUID.randomUUID().toString());
    school.setCode(UUID.randomUUID().toString());
    school.setName(UUID.randomUUID().toString());
    Set<SchoolService.Dto> schools = new HashSet<>();
    schools.add(school);
    
    school = new SchoolService.Dto();
    school.setIdentifier("1");
    schools.add(school);
    
    SchoolService schoolService = Mockito.mock(SchoolService.class);
    Mockito.when(schoolService.getAll()).thenReturn(schools);
    QuarkusMock.installMockForType(schoolService, SchoolService.class, RestClient.LITERAL);

    SchoolRepatriateRequestDto request = new SchoolRepatriateRequestDto();
    request.setAuditWho("christian");
    long count = count(entityManager, School.ENTITY_NAME);
    repatriateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, School.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/schoolbusiness.sql");
    }
  }
}
