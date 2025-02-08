package org.cyk.system.poulsscolaire.server.impl.business.feecategory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ci.gouv.dgbf.extension.server.business.BusinessInputValidationException;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.DeleteOneRequestDto;
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
import org.cyk.system.poulsscolaire.server.api.fee.StockDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementService.StockMovementCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockMovementService.StockMovementUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockService.StockCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.StockService.StockUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockMapper;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stock.StockValidator;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementMapper;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.stockmovement.StockMovementValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategory;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeCategoryDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.Stock;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovement;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockMovementDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.StockQuantity;
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
  FeeCategoryCreateBusiness feeCategoryCreateBusiness;

  @Inject
  FeeCategoryReadManyBusiness feeCategoryReadManyBusiness;

  @Inject
  FeeCategoryReadOneBusiness feeCategoryReadOneBusiness;

  @Inject
  FeeCategoryReadByIdentifierBusiness feeCategoryReadByIdentifierBusiness;

  @Inject
  FeeCategoryUpdateBusiness feeCategoryUpdateBusiness;

  @Inject
  FeeCategoryDeleteBusiness feeCategoryDeleteBusiness;

  @Inject
  FeeCategoryMapper feeCategoryMapper;

  @Inject
  FeeCategoryDynamicQuery feeCategoryDynamicQuery;

  DynamicQueryParameters<FeeCategory> feeCategoryParameters = new DynamicQueryParameters<>();

  /* Stock */

  @Inject
  StockCreateBusiness stockCreateBusiness;

  @Inject
  StockReadManyBusiness stockReadManyBusiness;

  @Inject
  StockReadOneBusiness stockReadOneBusiness;

  @Inject
  StockReadByIdentifierBusiness stockReadByIdentifierBusiness;

  @Inject
  StockUpdateBusiness stockUpdateBusiness;

  @Inject
  StockDeleteBusiness stockDeleteBusiness;

  @Inject
  StockMapper stockMapper;

  @Inject
  StockDynamicQuery stockDynamicQuery;

  DynamicQueryParameters<Stock> stockParameters = new DynamicQueryParameters<>();

  @Inject
  StockValidator stockValidator;

  /* Stock Movement */

  @Inject
  StockMovementCreateBusiness stockMovementCreateBusiness;

  @Inject
  StockMovementReadManyBusiness stockMovementReadManyBusiness;

  @Inject
  StockMovementReadOneBusiness stockMovementReadOneBusiness;

  @Inject
  StockMovementReadByIdentifierBusiness stockMovementReadByIdentifierBusiness;

  @Inject
  StockMovementUpdateBusiness stockMovementUpdateBusiness;

  @Inject
  StockMovementDeleteBusiness stockMovementDeleteBusiness;

  @Inject
  StockMovementMapper stockMovementMapper;

  @Inject
  StockMovementDynamicQuery stockMovementDynamicQuery;

  DynamicQueryParameters<StockMovement> stockMovementParameters = new DynamicQueryParameters<>();

  @Inject
  StockMovementValidator stockMovementValidator;

  @Test
  void create() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, FeeCategory.ENTITY_NAME);
    feeCategoryCreateBusiness.process(request);
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
    feeCategoryCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, FeeCategory.ENTITY_NAME));
  }

  @Test
  void create_whenExistingCode_whenSameSchoolIdentifier() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode("existingcode");
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier("1");
    request.setAuditWho("christian");
    assertThrows(BusinessInputValidationException.class,
        () -> feeCategoryCreateBusiness.process(request));
  }

  @Test
  void create_whenSchoolIdentifierExisting_whenSameCode() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode("1");
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier("existingschool");
    request.setAuditWho("christian");
    assertThrows(BusinessInputValidationException.class,
        () -> feeCategoryCreateBusiness.process(request));
  }

  @Test
  void create_whenSchoolIdentifierExisting_whenDifferentCode() {
    FeeCategoryCreateRequestDto request = new FeeCategoryCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier("existingschool");
    request.setAuditWho("christian");
    long count = count(entityManager, FeeCategory.ENTITY_NAME);
    feeCategoryCreateBusiness.process(request);
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
    feeCategoryUpdateBusiness.process(request);
    assertEquals(count + 0, count(entityManager, FeeCategory.ENTITY_NAME));
  }

  @Test
  void mapToDto_whenNull() {
    assertNull(feeCategoryMapper.mapToDto(null));
  }

  @Test
  void mapToDto_whenNotNull() {
    FeeCategory instance = new FeeCategory();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    FeeCategoryDto dto = feeCategoryMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    FeeCategory instance = new FeeCategory();
    instance.setIdentifier("1");
    FeeCategoryDto dto = feeCategoryMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void mapFromDto_whenNull() {
    assertNull(feeCategoryMapper.mapFromDto(null));
  }

  @Test
  void mapFromDto_whenAuditNull() {
    FeeCategoryDto dto = new FeeCategoryDto();
    dto.setIdentifier("1");
    FeeCategory instance = feeCategoryMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void mapFromDto_whenAuditNotNull() {
    FeeCategoryDto dto = new FeeCategoryDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    FeeCategory instance = feeCategoryMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  @ParameterizedTest
  @CsvFileSource(resources = {"feecategorydynamicquery_buildquery_projection.csv"},
      useHeadersInDisplayName = true)
  void buildQuery_amount(String amount, String expected) {
    feeCategoryParameters.projection().addNames(amount);
    assertEquals(expected, feeCategoryDynamicQuery.buildQueryString(feeCategoryParameters));
  }

  @ParameterizedTest
  @CsvSource({"1,90 000", "2,30 000"})
  void getToPay(String identifier, String expected) {
    feeCategoryParameters.projection().addNames(FeeCategoryDto.JSON_TOTAL_AMOUNT_AS_STRING);
    feeCategoryParameters.setResultMode(ResultMode.ONE);
    feeCategoryParameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = feeCategoryDynamicQuery.getOne(feeCategoryParameters);
    assertEquals(expected, feeCategory.totalAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,20 000", "2,12 000"})
  void getRegistrationToPay(String identifier, String expected) {
    feeCategoryParameters.projection()
        .addNames(FeeCategoryDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING);
    feeCategoryParameters.setResultMode(ResultMode.ONE);
    feeCategoryParameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = feeCategoryDynamicQuery.getOne(feeCategoryParameters);
    assertEquals(expected, feeCategory.totalRegistrationAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,5", "2,30 000"})
  void getPaid(String identifier, String expected) {
    feeCategoryParameters.projection().addNames(FeeCategoryDto.JSON_PAID_AMOUNT_AS_STRING);
    feeCategoryParameters.setResultMode(ResultMode.ONE);
    feeCategoryParameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = feeCategoryDynamicQuery.getOne(feeCategoryParameters);
    assertEquals(expected, feeCategory.paidAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,5", "2,12 000"})
  void getRegistrationPaid(String identifier, String expected) {
    feeCategoryParameters.projection()
        .addNames(FeeCategoryDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING);
    feeCategoryParameters.setResultMode(ResultMode.ONE);
    feeCategoryParameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = feeCategoryDynamicQuery.getOne(feeCategoryParameters);
    assertEquals(expected, feeCategory.paidRegistrationAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,89 995", "2,0"})
  void getPayable(String identifier, String expected) {
    feeCategoryParameters.projection().addNames(FeeCategoryDto.JSON_PAYABLE_AMOUNT_AS_STRING);
    feeCategoryParameters.setResultMode(ResultMode.ONE);
    feeCategoryParameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = feeCategoryDynamicQuery.getOne(feeCategoryParameters);
    assertEquals(expected, feeCategory.payableAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,19 995", "2,0"})
  void getRegistrationPayable(String identifier, String expected) {
    feeCategoryParameters.projection()
        .addNames(FeeCategoryDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING);
    feeCategoryParameters.setResultMode(ResultMode.ONE);
    feeCategoryParameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = feeCategoryDynamicQuery.getOne(feeCategoryParameters);
    assertEquals(expected, feeCategory.payableRegistrationAmountAsString);
  }

  @Test
  void projectionsGroup() {
    feeCategoryParameters.projection().addNames(FeeCategoryDto.JSON_SCHOOL_IDENTIFIER,
        FeeCategoryDto.JSON_SCHOOL_AS_STRING);
    feeCategoryParameters.setResultMode(ResultMode.ONE);
    feeCategoryParameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, "1");
    assertDoesNotThrow(() -> feeCategoryDynamicQuery.getOne(feeCategoryParameters));
  }

  /* Stock */

  @Test
  void stock_create() {
    StockCreateRequestDto request = new StockCreateRequestDto();
    request.setName(UUID.randomUUID().toString());
    request.setFeeCategoryIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, Stock.ENTITY_NAME);
    stockCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Stock.ENTITY_NAME));
  }

  @Test
  void stock_update() {
    StockUpdateRequestDto request = new StockUpdateRequestDto();
    request.setIdentifier("stocktoupdate");
    request.setName(UUID.randomUUID().toString());
    request.setFeeCategoryIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, Stock.ENTITY_NAME);
    stockUpdateBusiness.process(request);
    assertEquals(count + 0, count(entityManager, Stock.ENTITY_NAME));
  }

  @Test
  void stock_mapToDto_whenNull() {
    assertNull(stockMapper.mapToDto(null));
  }

  @Test
  void stock_mapToDto_whenNotNull() {
    Stock instance = new Stock();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    StockDto dto = stockMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void stock_mapToDto_whenNotNullAndAuditNull() {
    Stock instance = new Stock();
    instance.setIdentifier("1");
    StockDto dto = stockMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void stock_mapFromDto_whenNull() {
    assertNull(stockMapper.mapFromDto(null));
  }

  @Test
  void stock_mapFromDto_whenAuditNull() {
    StockDto dto = new StockDto();
    dto.setIdentifier("1");
    Stock instance = stockMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void stock_mapFromDto_whenAuditNotNull() {
    StockDto dto = new StockDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Stock instance = stockMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  /* Stock Movement */

  @Test
  void stockMovement_create() {
    StockMovementCreateRequestDto request = new StockMovementCreateRequestDto();
    request.setStockIdentifier("1");
    request.setQuantity(1);
    request.setReason("r");
    request.setAuditWho("christian");
    long count = count(entityManager, StockMovement.ENTITY_NAME);
    stockMovementCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, StockMovement.ENTITY_NAME));
  }

  @Test
  void stockMovement_update() {
    StockMovementUpdateRequestDto request = new StockMovementUpdateRequestDto();
    request.setIdentifier("stockmovementtoupdate");
    request.setStockIdentifier("1");
    request.setQuantity(1);
    request.setAuditWho("christian");
    long count = count(entityManager, StockMovement.ENTITY_NAME);
    stockMovementUpdateBusiness.process(request);
    assertEquals(count + 0, count(entityManager, StockMovement.ENTITY_NAME));
  }

  @Test
  void stockMovement_update_whenQuantityZero() {
    StockMovementUpdateRequestDto request = new StockMovementUpdateRequestDto();
    request.setIdentifier("stockmovementtoupdate");
    request.setStockIdentifier("1");
    request.setQuantity(0);
    request.setAuditWho("christian");
    assertThrows(BusinessInputValidationException.class,
        () -> stockMovementUpdateBusiness.process(request));
  }

  @Test
  void stockMovement_delete() {
    DeleteOneRequestDto request = new DeleteOneRequestDto();
    request.setIdentifier("stockmovementtodelete");
    request.setAuditWho("christian");
    long count = count(entityManager, StockMovement.ENTITY_NAME);
    stockMovementDeleteBusiness.process(request);
    assertEquals(count - 1, count(entityManager, StockMovement.ENTITY_NAME));
  }

  @Test
  void stockMovement_mapToDto_whenNull() {
    assertNull(stockMovementMapper.mapToDto(null));
  }

  @Test
  void stockMovement_mapToDto_whenNotNull() {
    StockMovement instance = new StockMovement();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    StockMovementDto dto = stockMovementMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void stockMovement_mapToDto_whenNotNullAndAuditNull() {
    StockMovement instance = new StockMovement();
    instance.setIdentifier("1");
    StockMovementDto dto = stockMovementMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void stockMovement_mapFromDto_whenNull() {
    assertNull(stockMovementMapper.mapFromDto(null));
  }

  @Test
  void stockMovement_mapFromDto_whenAuditNull() {
    StockMovementDto dto = new StockMovementDto();
    dto.setIdentifier("1");
    StockMovement instance = stockMovementMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void stockMovement_mapFromDto_whenAuditNotNull() {
    StockMovementDto dto = new StockMovementDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    StockMovement instance = stockMovementMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  @Test
  void instantiate() {
    assertNotNull(stockValidator.toString());
    assertNotNull(stockMovementValidator.toString());

    assertNotNull(stockDynamicQuery.toString());
    assertNotNull(stockMovementDynamicQuery.toString());

    assertNotNull(new StockQuantity());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/feecategorybusiness.sql");
    }
  }
}
