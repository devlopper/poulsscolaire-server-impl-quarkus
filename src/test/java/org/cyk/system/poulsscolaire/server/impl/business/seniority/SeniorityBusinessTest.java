package org.cyk.system.poulsscolaire.server.impl.business.seniority;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityService.SeniorityCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Seniority;
import org.junit.jupiter.api.Test;

@QuarkusTest
class SeniorityBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  SeniorityCreateBusiness createBusiness;

  @Inject
  SeniorityReadManyBusiness readManyBusiness;
  
  @Inject
  SeniorityReadOneBusiness readOneBusiness;
  
  @Inject
  SeniorityReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  SeniorityUpdateBusiness updateBusiness;
  
  @Inject
  SeniorityDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    SeniorityCreateRequestDto request = new SeniorityCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, Seniority.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Seniority.ENTITY_NAME));
  }
}
