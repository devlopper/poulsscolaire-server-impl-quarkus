package org.cyk.system.poulsscolaire.server.impl.business.period;

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
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodService.PeriodRepatriateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Period;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

  @Inject
  PeriodRepatriateBusiness repatriateBusiness;

  @Test
  void repatriate() {
    PeriodService.Dto dto = new PeriodService.Dto();
    dto.setIdentifier(UUID.randomUUID().toString());
    dto.setName(UUID.randomUUID().toString());
    Set<PeriodService.Dto> schools = new HashSet<>();
    schools.add(dto);
    
    dto = new PeriodService.Dto();
    dto.setIdentifier("1");
    schools.add(dto);
    
    PeriodService schoolService = Mockito.mock(PeriodService.class);
    Mockito.when(schoolService.getAll()).thenReturn(schools);
    QuarkusMock.installMockForType(schoolService, PeriodService.class, RestClient.LITERAL);

    PeriodRepatriateRequestDto request = new PeriodRepatriateRequestDto();
    request.setAuditWho("christian");
    long count = count(entityManager, Period.ENTITY_NAME);
    repatriateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Period.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/periodbusiness.sql");
    }
  }
}
