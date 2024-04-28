package org.cyk.system.poulsscolaire.server.impl.business.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentService.PaymentCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(PaymentBusinessTest.Profile.class)
class PaymentBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  PaymentCreateBusiness createBusiness;

  @Inject
  PaymentReadManyBusiness readManyBusiness;
  
  @Inject
  PaymentReadOneBusiness readOneBusiness;
  
  @Inject
  PaymentReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  PaymentUpdateBusiness updateBusiness;
  
  @Inject
  PaymentDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    PaymentCreateRequestDto request = new PaymentCreateRequestDto();
    request.setModeIdentifier("1");
    request.setAmount(100);
    request.setAuditWho("christian");
    long count = count(entityManager, Payment.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Payment.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/paymentbusiness.sql");
    }
  }
}
