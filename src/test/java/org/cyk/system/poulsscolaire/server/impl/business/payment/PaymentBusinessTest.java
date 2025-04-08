package org.cyk.system.poulsscolaire.server.impl.business.payment;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import ci.gouv.dgbf.extension.server.business.BusinessInputValidationException;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentAdjustedFeeService.PaymentAdjustedFeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentModeService.PaymentModeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentService.PaymentCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeMapper;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentadjustedfee.PaymentAdjustedFeeUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.paymentmode.PaymentModeUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecution;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecutionPersistence;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFee;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAdjustedFeeDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAmounts;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentAudits;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.PaymentMode;
import org.cyk.system.poulsscolaire.server.impl.persistence.SchoolConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

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

  /* PaymentAdjustedFee */

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

  @Inject
  PaymentAdjustedFeeMapper paymentAdjustedFeeMapper;

  /* PaymentMode */

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
  void create_getConfiguredFunding_whenSchoolConfigurationNull() {
    assertNull(createBusiness.getConfiguredFunding(null, null));
  }

  @Test
  void create_getConfiguredFunding_whenSchoolConfigurationNotNull() {
    FundingDynamicQuery fundingDynamicQuery = Mockito.mock(FundingDynamicQuery.class);
    Funding funding = new Funding();
    funding.identifier = UUID.randomUUID().toString();
    Mockito.when(fundingDynamicQuery.getOne(any())).thenReturn(funding);
    QuarkusMock.installMockForType(fundingDynamicQuery, FundingDynamicQuery.class);
    SchoolConfiguration schoolConfiguration = new SchoolConfiguration();
    assertNull(createBusiness.getConfiguredFunding(LocalDateTime.now(), schoolConfiguration));
  }

  @Test
  void create_instantiateFundingExecution_whenFundingNull() {
    assertNull(createBusiness.instantiateFundingExecution(null, null, null));
  }

  @Test
  void create_instantiateFundingExecution_whenFundingNotNull() {
    assertNotNull(createBusiness.instantiateFundingExecution(new Payment(), LocalDateTime.now(),
        new Funding()));
  }

  @Test
  void create_createFundingExecution_whenFundingNull() {
    Payment payment = new Payment();
    payment.audit = new Audit();
    assertDoesNotThrow(() -> createBusiness.createFundingExecution(payment, null));
  }

  @Test
  void create_createFundingExecution_whenFundingNotNull_whenFundingExecutionNull() {
    createBusiness = new PaymentCreateBusiness() {
      {
        fundingExecutionPersistence = Mockito.mock(FundingExecutionPersistence.class);

      }

      @Override
      Funding getConfiguredFunding(LocalDateTime date, SchoolConfiguration schoolConfiguration) {
        return new Funding();
      }

      @Override
      FundingExecution instantiateFundingExecution(Payment payment, LocalDateTime date,
          Funding funding) {
        return null;
      }
    };

    Payment payment = new Payment();
    payment.date = LocalDateTime.now();
    payment.audit = new Audit();
    SchoolConfiguration schoolConfiguration = new SchoolConfiguration();
    assertDoesNotThrow(() -> createBusiness.createFundingExecution(payment, schoolConfiguration));
  }
  
  @Test
  void create_createFundingExecution_whenFundingNotNull_whenFundingExecutionNotNull() {
    createBusiness = new PaymentCreateBusiness() {
      {
        fundingExecutionPersistence = Mockito.mock(FundingExecutionPersistence.class);

      }

      @Override
      Funding getConfiguredFunding(LocalDateTime date, SchoolConfiguration schoolConfiguration) {
        return new Funding();
      }

      @Override
      FundingExecution instantiateFundingExecution(Payment payment, LocalDateTime date,
          Funding funding) {
        return new FundingExecution();
      }
    };

    Payment payment = new Payment();
    payment.date = LocalDateTime.now();
    payment.audit = new Audit();
    SchoolConfiguration schoolConfiguration = new SchoolConfiguration();
    assertDoesNotThrow(() -> createBusiness.createFundingExecution(payment, schoolConfiguration));
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

  @Test
  void paymentAdjustedFee_mapToDto_whenNull() {
    assertNull(paymentAdjustedFeeMapper.mapToDto(null));
  }

  @Test
  void paymentAdjustedFee_mapToDto_whenNotNull() {
    PaymentAdjustedFee instance = new PaymentAdjustedFee();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    PaymentAdjustedFeeDto dto = paymentAdjustedFeeMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void paymentAdjustedFee_mapToDto_whenNotNullAndAuditNull() {
    PaymentAdjustedFee instance = new PaymentAdjustedFee();
    instance.setIdentifier("1");
    PaymentAdjustedFeeDto dto = paymentAdjustedFeeMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void paymentAdjustedFee_mapFromDto_whenNull() {
    assertNull(paymentAdjustedFeeMapper.mapFromDto(null));
  }

  @Test
  void paymentAdjustedFee_mapFromDto_whenAuditNull() {
    PaymentAdjustedFeeDto dto = new PaymentAdjustedFeeDto();
    dto.setIdentifier("1");
    PaymentAdjustedFee instance = paymentAdjustedFeeMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void paymentAdjustedFee_mapFromDto_whenAuditNotNull() {
    PaymentAdjustedFeeDto dto = new PaymentAdjustedFeeDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    PaymentAdjustedFee instance = paymentAdjustedFeeMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  @Test
  void paymentAdjustedFee_mapFromDto_whenAmountNotNull() {
    PaymentAdjustedFeeDto dto = new PaymentAdjustedFeeDto();
    dto.setIdentifier("1");
    dto.setAmount(5);
    PaymentAdjustedFee instance = paymentAdjustedFeeMapper.mapFromDto(dto);
    assertEquals(dto.getAmount(), instance.amount);
  }

  /* PaymentMode */

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

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/paymentbusiness.sql",
          "quarkus.hibernate-envers.enabled", "true");
    }
  }
}
