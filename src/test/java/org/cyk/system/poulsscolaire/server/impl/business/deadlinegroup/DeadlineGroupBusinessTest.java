package org.cyk.system.poulsscolaire.server.impl.business.deadlinegroup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.DeadlineGroupService.DeadlineGroupCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineGroup;
import org.junit.jupiter.api.Test;

@QuarkusTest
class DeadlineGroupBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  DeadlineGroupCreateBusiness createBusiness;

  @Inject
  DeadlineGroupReadManyBusiness readManyBusiness;
  
  @Inject
  DeadlineGroupReadOneBusiness readOneBusiness;
  
  @Inject
  DeadlineGroupReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  DeadlineGroupUpdateBusiness updateBusiness;
  
  @Inject
  DeadlineGroupDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    DeadlineGroupCreateRequestDto request = new DeadlineGroupCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, DeadlineGroup.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, DeadlineGroup.ENTITY_NAME));
  }
}
