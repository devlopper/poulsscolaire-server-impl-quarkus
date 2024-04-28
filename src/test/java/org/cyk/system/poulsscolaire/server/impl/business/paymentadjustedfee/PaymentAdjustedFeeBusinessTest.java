package org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeService.PaymentAdjustedFeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFee;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(PaymentAdjustedFeeBusinessTest.Profile.class)
class PaymentAdjustedFeeBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  PaymentAdjustedFeeCreateBusiness createBusiness;

  @Inject
  PaymentAdjustedFeeReadManyBusiness readManyBusiness;
  
  @Inject
  PaymentAdjustedFeeReadOneBusiness readOneBusiness;
  
  @Inject
  PaymentAdjustedFeeReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  PaymentAdjustedFeeUpdateBusiness updateBusiness;
  
  @Inject
  PaymentAdjustedFeeDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    PaymentAdjustedFeeCreateRequestDto request = new PaymentAdjustedFeeCreateRequestDto();
    request.setPaymentIdentifier("1");
    request.setAdjustedFeeIdentifier("1");
    request.setAmount(75);
    request.setAuditWho("christian");
    long count = count(entityManager, PaymentAdjustedFee.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, PaymentAdjustedFee.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/paymentadjustedfeebusiness.sql");
    }
  }
}
