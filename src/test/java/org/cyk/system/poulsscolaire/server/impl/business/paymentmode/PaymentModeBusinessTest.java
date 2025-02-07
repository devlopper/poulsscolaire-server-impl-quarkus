package org.cyk.system.poulsscolaire.server.impl.business.paymentmode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentModeService.PaymentModeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentMode;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class PaymentModeBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  PaymentModeCreateBusiness paymentModeCreateBusiness;

  @Inject
  PaymentModeReadManyBusiness paymentModeReadManyBusiness;
  
  @Inject
  PaymentModeReadOneBusiness paymentModeReadOneBusiness;
  
  @Inject
  PaymentModeReadByIdentifierBusiness paymentModeReadByIdentifierBusiness;
  
  @Inject
  PaymentModeUpdateBusiness paymentModeUpdateBusiness;
  
  @Inject
  PaymentModeDeleteBusiness paymentModeDeleteBusiness;
  
  @Test
  void paymentMode_create() {
    PaymentModeCreateRequestDto request = new PaymentModeCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, PaymentMode.ENTITY_NAME);
    paymentModeCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, PaymentMode.ENTITY_NAME));
  }
}
