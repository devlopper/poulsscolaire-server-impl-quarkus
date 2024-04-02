package org.cyk.system.poulsscolaire.server.impl.business.identity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.IdentityService.IdentityCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.IdentityService.IdentityUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Identity;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(IdentityBusinessTest.Profile.class)
class IdentityBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  IdentityCreateBusiness createBusiness;

  @Inject
  IdentityReadManyBusiness readManyBusiness;
  
  @Inject
  IdentityReadOneBusiness readOneBusiness;
  
  @Inject
  IdentityReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  IdentityUpdateBusiness updateBusiness;
  
  @Inject
  IdentityDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    IdentityCreateRequestDto request = new IdentityCreateRequestDto();
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setGenderIdentifier("M");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Identity.ENTITY_NAME));
  }
  
  @Test
  void update() {
    IdentityUpdateRequestDto request = new IdentityUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setFirstName(UUID.randomUUID().toString());
    request.setLastNames(UUID.randomUUID().toString());
    request.setEmailAddress("m@m.com");
    request.setGenderIdentifier("M");
    request.setAuditWho("christian");
    long count = count(entityManager, Identity.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, Identity.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/identitybusiness.sql");
    }
  }
}
