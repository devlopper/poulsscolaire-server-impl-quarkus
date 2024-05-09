package org.cyk.system.poulsscolaire.server.impl.business.branch;

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
import org.cyk.system.poulsscolaire.server.api.configuration.BranchService.BranchRepatriateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
@TestProfile(BranchBusinessTest.Profile.class)
class BranchBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  BranchReadManyBusiness readManyBusiness;

  @Inject
  BranchReadOneBusiness readOneBusiness;

  @Inject
  BranchReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  BranchRepatriateBusiness repatriateBusiness;

  @Test
  void repatriate() {
    BranchService.Dto dto = new BranchService.Dto();
    dto.setIdentifier(UUID.randomUUID().toString());
    dto.setName(UUID.randomUUID().toString());
    Set<BranchService.Dto> schools = new HashSet<>();
    schools.add(dto);
    
    dto = new BranchService.Dto();
    dto.setIdentifier("1");
    schools.add(dto);
    
    BranchService schoolService = Mockito.mock(BranchService.class);
    Mockito.when(schoolService.getAll()).thenReturn(schools);
    QuarkusMock.installMockForType(schoolService, BranchService.class, RestClient.LITERAL);

    BranchRepatriateRequestDto request = new BranchRepatriateRequestDto();
    request.setAuditWho("christian");
    long count = count(entityManager, Branch.ENTITY_NAME);
    repatriateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Branch.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/branchbusiness.sql");
    }
  }
}
