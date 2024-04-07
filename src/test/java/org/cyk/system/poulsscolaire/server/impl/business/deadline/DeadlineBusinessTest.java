package org.cyk.system.poulsscolaire.server.impl.business.deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.DeadlineService.DeadlineCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(DeadlineBusinessTest.Profile.class)
class DeadlineBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  DeadlineCreateBusiness createBusiness;

  @Inject
  DeadlineReadManyBusiness readManyBusiness;
  
  @Inject
  DeadlineReadOneBusiness readOneBusiness;
  
  @Inject
  DeadlineReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  DeadlineUpdateBusiness updateBusiness;
  
  @Inject
  DeadlineDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    DeadlineCreateRequestDto request = new DeadlineCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setGroupIdentifier("1");
    request.setDate(LocalDateTime.now());
    request.setAuditWho("christian");
    long count = count(entityManager, Deadline.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Deadline.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/deadlinebusiness.sql");
    }
  }
}
