package ci.gouv.dgbf.system.poulsscolaire.server.impl.business.duegroup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup.DueGroupCreateBusiness;
import org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup.DueGroupDeleteBusiness;
import org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup.DueGroupReadByIdentifierBusiness;
import org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup.DueGroupReadManyBusiness;
import org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup.DueGroupReadOneBusiness;
import org.cyk.dgbf.system.poulsscolaire.server.impl.business.duegroup.DueGroupUpdateBusiness;
import org.cyk.dgbf.system.poulsscolaire.server.impl.persistence.DueGroup;
import org.cyk.system.poulsscolaire.server.api.DueGroupService.DueGroupCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.DueGroupService.GetManyResponseDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(DueGroupBusinessTest.Profile.class)
class DueGroupBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  DueGroupCreateBusiness createBusiness;

  @Inject
  DueGroupReadManyBusiness readManyBusiness;
  
  @Inject
  DueGroupReadOneBusiness readOneBusiness;
  
  @Inject
  DueGroupReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  DueGroupUpdateBusiness updateBusiness;
  
  @Inject
  DueGroupDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    DueGroupCreateRequestDto request = new DueGroupCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, DueGroup.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, DueGroup.ENTITY_NAME));
  }
  
  @Test
  void readMany() {
    GetManyRequestDto request = new GetManyRequestDto();    
    request.setAuditWho("christian");
    GetManyResponseDto response = readManyBusiness.process(request);
    assertEquals(response.getTotal(), count(entityManager, DueGroup.ENTITY_NAME));
  }
  
  /**
   * Cette classe représente le profile des tests.
   *
   * @author Christian
   *
   */
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/duegroupbusiness.sql");
    }
  }
}
