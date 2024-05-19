package org.cyk.system.poulsscolaire.server.impl.business.payment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ci.gouv.dgbf.extension.server.business.BusinessInputValidationException;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentService.PaymentCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFee;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

  @ParameterizedTest
  @CsvSource(value = {"2", "nofees", "unknown"})
  void create_whenPayablesEmpty(String registrationIdentifier) {
    PaymentCreateRequestDto request = new PaymentCreateRequestDto();
    request.setRegistrationIdentifier(registrationIdentifier);
    request.setModeIdentifier("1");
    request.setAmount(10);
    request.setAuditWho("christian");
    assertThrows(BusinessInputValidationException.class, () -> createBusiness.process(request));
  }

  @ParameterizedTest
  @CsvSource(value = {"onepayable_amountless,100,1", "onepayable_amountequal,1,1",
      "twopayables_amountless,25,2", "twopayables_oneoptional_amountless,9,1",
      "twopayables_amountlessone,9,1"})
  void create_whenOnePayable_whenAmountLess(String identifier, int amount,
      int numberofAdjustedPayment) {
    PaymentCreateRequestDto request = new PaymentCreateRequestDto();
    request.setRegistrationIdentifier(identifier);
    request.setModeIdentifier("1");
    request.setAmount(amount);
    request.setAuditWho("christian");
    long paymentCount = count(entityManager, Payment.ENTITY_NAME);
    long paymentAdjustedFeeCount = count(entityManager, PaymentAdjustedFee.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(paymentCount + 1, count(entityManager, Payment.ENTITY_NAME));
    assertEquals(paymentAdjustedFeeCount + numberofAdjustedPayment,
        count(entityManager, PaymentAdjustedFee.ENTITY_NAME));
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/paymentbusiness.sql");
    }
  }
}
