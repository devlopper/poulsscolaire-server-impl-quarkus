package org.cyk.system.poulsscolaire.server.impl.business.payment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ci.gouv.dgbf.extension.server.business.BusinessInputValidationException;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeService.PaymentAdjustedFeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentService.PaymentCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFeeDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAmounts;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAudits;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentDynamicQuery;
import org.junit.jupiter.api.Test;
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
  PaymentCancelBusiness cancelBusiness;

  @Inject
  PaymentDeleteBusiness deleteBusiness;

  @Inject
  PaymentDynamicQuery dynamicQuery;

  DynamicQueryParameters<Payment> parameters = new DynamicQueryParameters<>();

  /**/

  @Inject
  PaymentAdjustedFeeCreateBusiness paymentAdjustedFeeCreateBusiness;

  @Inject
  PaymentAdjustedFeeReadManyBusiness paymentAdjustedFeeReadManyBusiness;

  @Inject
  PaymentAdjustedFeeReadOneBusiness paymentAdjustedFeeReadOneBusiness;

  @Inject
  PaymentAdjustedFeeReadByIdentifierBusiness paymentAdjustedFeeReadByIdentifierBusiness;

  @Inject
  PaymentAdjustedFeeUpdateBusiness paymentAdjustedFeeUpdateBusiness;

  @Inject
  PaymentAdjustedFeeDeleteBusiness paymentAdjustedFeeDeleteBusiness;

  @Inject
  PaymentAdjustedFeeDynamicQuery paymentAdjustedFeeDynamicQuery;

  DynamicQueryParameters<PaymentAdjustedFee> parametersPaymentAdjustedFee =
      new DynamicQueryParameters<>();

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
  @CsvSource(value = {"payableisone,0"})
  void create_whenPayablesLessThanPreRegistrationAmount_thenThrows(String registrationIdentifier,
      Integer amount) {
    PaymentCreateRequestDto request = new PaymentCreateRequestDto();
    request.setRegistrationIdentifier(registrationIdentifier);
    request.setModeIdentifier("1");
    request.setAmount(amount);
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

  @Test
  void cancel() {
    ByIdentifierRequestDto request = new ByIdentifierRequestDto();
    request.setIdentifier("cancelable");
    request.setAuditWho("christian");
    assertDoesNotThrow(() -> cancelBusiness.process(request));
  }

  @Test
  void instanciate() {
    assertNotNull(new PaymentAmounts());
    assertNotNull(new PaymentAudits());
  }

  @Test
  void getMany() {
    parameters.projection().addNames(PaymentDto.JSON_IDENTIFIER,
        PaymentDto.JSON_REGISTRATION_AS_STRING, PaymentDto.JSON_AUDIT_CREATION_AS_STRING);
    assertEquals(2, dynamicQuery.getMany(parameters).size());
  }

  @Test
  void createPaymentAdjustedFee() {
    PaymentAdjustedFeeCreateRequestDto request = new PaymentAdjustedFeeCreateRequestDto();
    request.setPaymentIdentifier("notcanceled");
    request.setAdjustedFeeIdentifier("notcanceled");
    request.setAmount(75);
    request.setAuditWho("christian");
    long count = count(entityManager, PaymentAdjustedFee.ENTITY_NAME);
    paymentAdjustedFeeCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, PaymentAdjustedFee.ENTITY_NAME));
  }

  @Test
  void paymentAdjustedFeeDynamicQuery() {
    assertNotNull(paymentAdjustedFeeDynamicQuery.buildQuery(parametersPaymentAdjustedFee));
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/paymentbusiness.sql",
          "quarkus.hibernate-envers.enabled", "true");
    }
  }
}
