package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryService.FeeCategoryCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryService.FeeCategoryUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategoryDynamicQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(FeeCategoryBusinessTest.Profile.class)
class FeeCategoryBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  FeeCategoryCreateBusiness createBusiness;

  @Inject
  FeeCategoryReadManyBusiness readManyBusiness;
  
  @Inject
  FeeCategoryReadOneBusiness readOneBusiness;
  
  @Inject
  FeeCategoryReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  FeeCategoryUpdateBusiness updateBusiness;
  
  @Inject
  FeeCategoryDeleteBusiness deleteBusiness;
  
  @Inject
  FeeCategoryMapper mapper;
  
  @Inject
  FeeCategoryDynamicQuery dynamicQuery;

  DynamicQueryParameters<FeeCategory> parameters = new DynamicQueryParameters<>();
  
  @Test
  void create() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, FeeCategory.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, FeeCategory.ENTITY_NAME));
  }
  
  @Test
  void create_whenExistingCode_whenDifferentSchoolIdentifier() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode("existingcode");
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, FeeCategory.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, FeeCategory.ENTITY_NAME));
  }
  
  @Test
  void create_whenExistingCode_whenSameSchoolIdentifier() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode("existingcode");
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier("1");
    request.setAuditWho("christian");
    assertThrows(BusinessInputValidationException.class, () -> createBusiness.process(request));
  }
  
  @Test
  void create_whenSchoolIdentifierExisting_whenSameCode() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode("1");
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier("existingschool");
    request.setAuditWho("christian");
    assertThrows(BusinessInputValidationException.class, () -> createBusiness.process(request));
  }
  
  @Test
  void create_whenSchoolIdentifierExisting_whenDifferentCode() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier("existingschool");
    request.setAuditWho("christian");
    long count = count(entityManager, FeeCategory.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, FeeCategory.ENTITY_NAME));
  }
  
  @Test
  void update() {
    FeeCategoryUpdateRequestDto request = new FeeCategoryUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, FeeCategory.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count + 0, count(entityManager, FeeCategory.ENTITY_NAME));
  }
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    FeeCategory instance = new FeeCategory();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    FeeCategoryDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    FeeCategory instance = new FeeCategory();
    instance.setIdentifier("1");
    FeeCategoryDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    FeeCategoryDto dto = new FeeCategoryDto();
    dto.setIdentifier("1");
    FeeCategory instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    FeeCategoryDto dto = new FeeCategoryDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    FeeCategory instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  @ParameterizedTest
  @CsvFileSource(resources = {"feecategorydynamicquery_buildquery_projection.csv"},
      useHeadersInDisplayName = true)
  void buildQuery_amount(String amount, String expected) {
    parameters.projection().addNames(amount);
    assertEquals(expected, dynamicQuery.buildQueryString(parameters));
  }

  @ParameterizedTest
  @CsvSource({"1,90 000", "2,30 000"})
  void getToPay(String identifier, String expected) {
    parameters.projection().addNames(FeeCategoryDto.JSON_TOTAL_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(expected, feeCategory.totalAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,20 000", "2,12 000"})
  void getRegistrationToPay(String identifier, String expected) {
    parameters.projection().addNames(FeeCategoryDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(expected, feeCategory.totalRegistrationAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,5", "2,30 000"})
  void getPaid(String identifier, String expected) {
    parameters.projection().addNames(FeeCategoryDto.JSON_PAID_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(expected, feeCategory.paidAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,5", "2,12 000"})
  void getRegistrationPaid(String identifier, String expected) {
    parameters.projection().addNames(FeeCategoryDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(expected, feeCategory.paidRegistrationAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,89 995", "2,0"})
  void getPayable(String identifier, String expected) {
    parameters.projection().addNames(FeeCategoryDto.JSON_PAYABLE_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(expected, feeCategory.payableAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,19 995", "2,0"})
  void getRegistrationPayable(String identifier, String expected) {
    parameters.projection().addNames(FeeCategoryDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(expected, feeCategory.payableRegistrationAmountAsString);
  }

  @Test
  void projectionsGroup() {
    parameters.projection().addNames(FeeCategoryDto.JSON_SCHOOL_IDENTIFIER,
        FeeCategoryDto.JSON_SCHOOL_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, "1");
    assertDoesNotThrow(() -> dynamicQuery.getOne(parameters));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/feecategorybusiness.sql");
    }
  }
}
