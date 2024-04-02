package org.cyk.system.poulsscolaire.server.impl.business.assignmenttype;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.AssignmentTypeService.AssignmentTypeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AssignmentType;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AssignmentTypeBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  AssignmentTypeCreateBusiness createBusiness;

  @Inject
  AssignmentTypeReadManyBusiness readManyBusiness;
  
  @Inject
  AssignmentTypeReadOneBusiness readOneBusiness;
  
  @Inject
  AssignmentTypeReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  AssignmentTypeUpdateBusiness updateBusiness;
  
  @Inject
  AssignmentTypeDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    AssignmentTypeCreateRequestDto request = new AssignmentTypeCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, AssignmentType.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AssignmentType.ENTITY_NAME));
  }
}
