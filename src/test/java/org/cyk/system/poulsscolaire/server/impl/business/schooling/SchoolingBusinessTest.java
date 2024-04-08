package org.cyk.system.poulsscolaire.server.impl.business.schooling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.junit.jupiter.api.Test;

@QuarkusTest
class SchoolingBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  SchoolingCreateBusiness createBusiness;

  @Inject
  SchoolingReadManyBusiness readManyBusiness;
  
  @Inject
  SchoolingReadOneBusiness readOneBusiness;
  
  @Inject
  SchoolingReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  SchoolingUpdateBusiness updateBusiness;
  
  @Inject
  SchoolingDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    SchoolingCreateRequestDto request = new SchoolingCreateRequestDto();
    request.setSchoolIdentifier("1");
    request.setBranchIdentifier("1");
    request.setPeriodIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, Schooling.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Schooling.ENTITY_NAME));
  }
}
