package org.cyk.system.poulsscolaire.server.impl.business.paymentmode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.PaymentModeService.PaymentModeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentMode;
import org.junit.jupiter.api.Test;

@QuarkusTest
class PaymentModeBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  PaymentModeCreateBusiness createBusiness;

  @Inject
  PaymentModeReadManyBusiness readManyBusiness;
  
  @Inject
  PaymentModeReadOneBusiness readOneBusiness;
  
  @Inject
  PaymentModeReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  PaymentModeUpdateBusiness updateBusiness;
  
  @Inject
  PaymentModeDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    PaymentModeCreateRequestDto request = new PaymentModeCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, PaymentMode.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, PaymentMode.ENTITY_NAME));
  }
}
