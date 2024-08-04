package org.cyk.system.poulsscolaire.server.impl.business.period;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(PeriodBusinessTest.Profile.class)
class PeriodBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  PeriodReadManyBusiness readManyBusiness;

  @Inject
  PeriodReadOneBusiness readOneBusiness;

  @Inject
  PeriodReadByIdentifierBusiness readByIdentifierBusiness;

  @Test
  void getMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(1, readManyBusiness.process(request).getCount());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/periodbusiness.sql");
    }
  }
}
