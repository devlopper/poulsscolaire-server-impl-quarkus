package org.cyk.system.poulsscolaire.server.impl.business.cashregister;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.payment.CashRegisterService.CashRegisterCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.CashRegister;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CashRegisterBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  CashRegisterCreateBusiness createBusiness;

  @Inject
  CashRegisterReadManyBusiness readManyBusiness;
  
  @Inject
  CashRegisterReadOneBusiness readOneBusiness;
  
  @Inject
  CashRegisterReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  CashRegisterUpdateBusiness updateBusiness;
  
  @Inject
  CashRegisterDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    CashRegisterCreateRequestDto request = new CashRegisterCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, CashRegister.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, CashRegister.ENTITY_NAME));
  }
}
