package org.cyk.system.poulsscolaire.server.impl.business.duegroup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.DueGroupService.DueGroupCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.DueGroup;
import org.junit.jupiter.api.Test;

@QuarkusTest
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
}
