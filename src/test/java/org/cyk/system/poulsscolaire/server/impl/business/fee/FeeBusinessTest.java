package org.cyk.system.poulsscolaire.server.impl.business.fee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ci.gouv.dgbf.extension.server.business.BusinessInputValidationException;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.FeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeFilter;
import org.cyk.system.poulsscolaire.server.api.fee.FeeService.FeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeService.FeeUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.Fee;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeDynamicQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(FeeBusinessTest.Profile.class)
class FeeBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  FeeCreateBusiness createBusiness;

  @Inject
  FeeReadManyBusiness readManyBusiness;

  @Inject
  FeeReadOneBusiness readOneBusiness;

  @Inject
  FeeReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  FeeUpdateBusiness updateBusiness;

  @Inject
  FeeDeleteBusiness deleteBusiness;

  @Inject
  FeeMapper mapper;
  
  @Inject
  FeeDynamicQuery dynamicQuery;

  DynamicQueryParameters<Fee> dynamicQueryParameters = new DynamicQueryParameters<>();

  @Test
  void buildQuery_whenProjectionSchoolingSchoolAsString() {
    dynamicQueryParameters.projection().addNames(FeeDto.JSON_SCHOOLING_SCHOOL_AS_STRING);
    assertEquals(
        "SELECT school.name FROM Fee t "
            + "LEFT JOIN School school ON school.identifier = t.schooling.schoolIdentifier "
            + "ORDER BY t.amount.paymentOrderNumber ASC,t.category.name ASC",
        dynamicQuery.buildQueryString(dynamicQueryParameters));
  }

  @ParameterizedTest
  @CsvSource(value = {"1,1,1,100,0", "2,1,1,500,75"})
  void getOne_whenSumAmountAndRegistration_whenFilterSchoolingAssignlentTypeSeniority(
      String schoolingIdentifier, String assignmenttypeIdentifier, String seniorityIdentifier,
      String expectedAmountSum, String expectedRegistrationSum) {
    dynamicQueryParameters.setResultMode(ResultMode.ONE);
    dynamicQueryParameters.projection().addNames(FeeDto.JSON_AMOUNT_VALUE_SUM_AS_STRING,
        FeeDto.JSON_AMOUNT_REGISTRATION_SUM_AS_STRING);
    dynamicQueryParameters.filter().addCriteria(FeeFilter.JSON_SCHOOLING_IDENTIFIER,
        schoolingIdentifier);
    dynamicQueryParameters.filter().addCriteria(FeeFilter.JSON_ASSIGNMENT_TYPE_IDENTIFIER,
        assignmenttypeIdentifier);
    dynamicQueryParameters.filter().addCriteria(FeeFilter.JSON_SENIORITY_IDENTIFIER,
        seniorityIdentifier);
    Fee fee = dynamicQuery.getOne(dynamicQueryParameters);
    assertEquals(expectedAmountSum, fee.amountValueSumAsString);
    assertEquals(expectedRegistrationSum, fee.amountRegistrationSumAsString);
  }
  
  @Test
  void create() {
    FeeCreateRequestDto request = new FeeCreateRequestDto();
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setSchoolingIdentifier("1");
    request.setCategoryIdentifier("2");
    request.setOptional(true);
    request.setPaymentOrderNumber(2);
    request.setRenewable(true);
    request.setValue(1);
    request.setAuditWho("christian");
    long feeCount = count(entityManager, Fee.ENTITY_NAME);
    long amountCount = count(entityManager, Amount.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(feeCount + 1, count(entityManager, Fee.ENTITY_NAME));
    assertEquals(amountCount + 1, count(entityManager, Amount.ENTITY_NAME));
  }
  
  @Test
  void create_whenValueZero() {
    FeeCreateRequestDto request = new FeeCreateRequestDto();
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setSchoolingIdentifier("1");
    request.setCategoryIdentifier("1");
    request.setOptional(true);
    request.setPaymentOrderNumber(0);
    request.setRegistrationValuePart(0);
    request.setRenewable(true);
    request.setValue(0);
    request.setAuditWho("christian");
    long feeCount = count(entityManager, Fee.ENTITY_NAME);
    long amountCount = count(entityManager, Amount.ENTITY_NAME);
    assertThrows(BusinessInputValidationException.class, () -> createBusiness.process(request));
    assertEquals(feeCount, count(entityManager, Fee.ENTITY_NAME));
    assertEquals(amountCount, count(entityManager, Amount.ENTITY_NAME));
  }
  
  @Test
  void create_whenRegistrationValuePartGreaterThanValue() {
    FeeCreateRequestDto request = new FeeCreateRequestDto();
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setSchoolingIdentifier("1");
    request.setCategoryIdentifier("1");
    request.setOptional(true);
    request.setPaymentOrderNumber(0);
    request.setRegistrationValuePart(2);
    request.setRenewable(true);
    request.setValue(1);
    request.setAuditWho("christian");
    long feeCount = count(entityManager, Fee.ENTITY_NAME);
    long amountCount = count(entityManager, Amount.ENTITY_NAME);
    assertThrows(BusinessInputValidationException.class, () -> createBusiness.process(request));
    assertEquals(feeCount, count(entityManager, Fee.ENTITY_NAME));
    assertEquals(amountCount, count(entityManager, Amount.ENTITY_NAME));
  }
  
  @Test
  void update() {
    FeeUpdateRequestDto request = new FeeUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setAssignmentTypeIdentifier("forupdate");
    request.setSeniorityIdentifier("1");
    request.setSchoolingIdentifier("1");
    request.setCategoryIdentifier("1");
    request.setOptional(true);
    request.setPaymentOrderNumber(0);
    request.setRegistrationValuePart(0);
    request.setRenewable(true);
    request.setValue(0);
    request.setAuditWho("christian");
    long count = count(entityManager, Fee.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, Fee.ENTITY_NAME));
  }
  
  @Test
  void updateOptional_whenOptionalTrue() {
    FeeUpdateRequestDto request = new FeeUpdateRequestDto();
    request.setIdentifier("toupdatewhenoptionaltrue");
    request.setAssignmentTypeIdentifier("1");
    request.setSeniorityIdentifier("1");
    request.setSchoolingIdentifier("1");
    request.setCategoryIdentifier("3");
    request.setOptional(false);
    request.setRegistrationValuePart(0);
    request.setRenewable(true);
    request.setPaymentOrderNumber(0);
    request.setValue(0);
    request.setAuditWho("christian");
    updateBusiness.process(request);
    Fee fee = entityManager.find(Fee.class, request.getIdentifier());
    assertFalse(fee.amount.optional);
    assertNotNull(fee.amount.paymentOrderNumber);
  }
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    Fee instance = new Fee();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    FeeDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    Fee instance = new Fee();
    instance.setIdentifier("1");
    FeeDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    FeeDto dto = new FeeDto();
    dto.setIdentifier("1");
    Fee instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    FeeDto dto = new FeeDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Fee instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/feebusiness.sql");
    }
  }
}
