package org.cyk.system.poulsscolaire.server.impl.business.branch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.BranchService.BranchCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.junit.jupiter.api.Test;

@QuarkusTest
class BranchBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  BranchCreateBusiness createBusiness;

  @Inject
  BranchReadManyBusiness readManyBusiness;
  
  @Inject
  BranchReadOneBusiness readOneBusiness;
  
  @Inject
  BranchReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  BranchUpdateBusiness updateBusiness;
  
  @Inject
  BranchDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    BranchCreateRequestDto request = new BranchCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, Branch.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Branch.ENTITY_NAME));
  }
}
