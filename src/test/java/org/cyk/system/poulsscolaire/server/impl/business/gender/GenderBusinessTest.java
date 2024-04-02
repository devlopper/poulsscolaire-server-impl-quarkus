package org.cyk.system.poulsscolaire.server.impl.business.gender;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.GenderService.GenderCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Gender;
import org.junit.jupiter.api.Test;

@QuarkusTest
class GenderBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  GenderCreateBusiness createBusiness;

  @Inject
  GenderReadManyBusiness readManyBusiness;
  
  @Inject
  GenderReadOneBusiness readOneBusiness;
  
  @Inject
  GenderReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  GenderUpdateBusiness updateBusiness;
  
  @Inject
  GenderDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    GenderCreateRequestDto request = new GenderCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, Gender.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Gender.ENTITY_NAME));
  }
}
