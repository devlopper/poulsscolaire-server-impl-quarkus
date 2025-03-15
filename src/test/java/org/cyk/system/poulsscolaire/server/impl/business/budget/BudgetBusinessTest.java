package org.cyk.system.poulsscolaire.server.impl.business.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.ByFilterRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.time.Month;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService.BudgetCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService.BudgetReturnRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService.BudgetUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetStatus;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionService.FundingExecutionCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionService.FundingExecutionUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingFilter;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.ByFilterWithReasonRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingReturnRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingUpdateAmountRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingSourceService.FundingSourceCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingStatus;
import org.cyk.system.poulsscolaire.server.api.configuration.DepartmentDto;
import org.cyk.system.poulsscolaire.server.impl.business.department.DepartmentMapper;
import org.cyk.system.poulsscolaire.server.impl.business.department.DepartmentReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.department.DepartmentReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.department.DepartmentReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingAcceptBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingAcceptByFilterBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingApproveBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingApproveByFilterBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingMapper;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReturnBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingReturnByFilterBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingTransmitBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingTransmitByFilterBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingUpdateAmountBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.funding.FundingValidator;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionMapper;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionValidator;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceMapper;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingsource.FundingSourceUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetAmount;
import org.cyk.system.poulsscolaire.server.impl.persistence.Department;
import org.cyk.system.poulsscolaire.server.impl.persistence.DepartmentDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecution;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSource;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingSourceDynamicQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@QuarkusTest
@TestProfile(BudgetBusinessTest.Profile.class)
class BudgetBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  BudgetCreateBusiness createBusiness;

  @Inject
  BudgetTransmitBusiness transmitBusiness;

  @Inject
  BudgetAcceptBusiness acceptBusiness;

  @Inject
  BudgetApproveBusiness approveBusiness;

  @Inject
  BudgetReturnBusiness returnBusiness;

  @Inject
  BudgetReadManyBusiness readManyBusiness;

  @Inject
  BudgetReadOneBusiness readOneBusiness;

  @Inject
  BudgetReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  BudgetUpdateBusiness updateBusiness;

  @Inject
  BudgetDeleteBusiness deleteBusiness;

  @Inject
  BudgetValidator validator;

  @Inject
  BudgetMapper mapper;

  /* Department */
  
  @Inject
  DepartmentReadManyBusiness departmentReadManyBusiness;

  @Inject
  DepartmentReadOneBusiness departmentReadOneBusiness;

  @Inject
  DepartmentReadByIdentifierBusiness departmentReadByIdentifierBusiness;

  @Inject
  DepartmentDynamicQuery departmentDynamicQuery;

  DynamicQueryParameters<Department> departmentParameters = new DynamicQueryParameters<>();
  
  @Inject
  DepartmentMapper departmentMapper;
  
  /* FundingSource */
  
  @Inject
  FundingSourceCreateBusiness fundingSourceCreateBusiness;

  @Inject
  FundingSourceReadManyBusiness fundingSourceReadManyBusiness;

  @Inject
  FundingSourceReadOneBusiness fundingSourceReadOneBusiness;

  @Inject
  FundingSourceReadByIdentifierBusiness fundingSourceReadByIdentifierBusiness;

  @Inject
  FundingSourceUpdateBusiness fundingSourceUpdateBusiness;

  @Inject
  FundingSourceDeleteBusiness fundingSourceDeleteBusiness;

  @Inject
  FundingSourceMapper fundingSourceMapper;

  @Inject
  FundingSourceDynamicQuery fundingSourceDynamicQuery;

  DynamicQueryParameters<FundingSource> fundingSourceDynamicQueryParameters =
      new DynamicQueryParameters<>();
  
  /* Funding */
  
  @Inject
  FundingCreateBusiness fundingCreateBusiness;

  @Inject
  FundingTransmitBusiness fundingTransmitBusiness;

  @Inject
  FundingTransmitByFilterBusiness fundingTransmitByFilterBusiness;
  
  @Inject
  FundingAcceptBusiness fundingAcceptBusiness;

  @Inject
  FundingAcceptByFilterBusiness fundingAcceptByFilterBusiness;
  
  @Inject
  FundingApproveBusiness fundingApproveBusiness;

  @Inject
  FundingApproveByFilterBusiness fundingApproveByFilterBusiness;
  
  @Inject
  FundingReturnBusiness fundingReturnBusiness;
  
  @Inject
  FundingReturnByFilterBusiness fundingReturnByFilterBusiness;
  
  @Inject
  FundingReadManyBusiness fundingReadManyBusiness;

  @Inject
  FundingReadOneBusiness fundingReadOneBusiness;

  @Inject
  FundingReadByIdentifierBusiness fundingReadByIdentifierBusiness;

  @Inject
  FundingUpdateBusiness fundingUpdateBusiness;

  @Inject
  FundingUpdateAmountBusiness fundingUpdateAmountBusiness;

  @Inject
  FundingDeleteBusiness fundingDeleteBusiness;

  @Inject
  FundingValidator fundingValidator;

  @Inject
  FundingMapper fundingMapper;

  /* Execution */

  @Inject
  FundingExecutionCreateBusiness executionCreateBusiness;

  @Inject
  FundingExecutionReadManyBusiness executionReadManyBusiness;

  @Inject
  FundingExecutionReadOneBusiness executionReadOneBusiness;

  @Inject
  FundingExecutionReadByIdentifierBusiness executionReadByIdentifierBusiness;

  @Inject
  FundingExecutionUpdateBusiness executionUpdateBusiness;

  @Inject
  FundingExecutionDeleteBusiness executionDeleteBusiness;

  @Inject
  FundingExecutionValidator executionValidator;

  @Inject
  FundingExecutionMapper executionMapper;
  
  @Test
  void create() {
    BudgetCreateRequestDto request = new BudgetCreateRequestDto();
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setAccountingPlanIdentifier("1");
    request.setYear(2026);
    request.setAuditWho("christian");
    long count = count(entityManager, Budget.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Budget.ENTITY_NAME));
  }

  @ParameterizedTest
  @ValueSource(strings = {"transmit_when_created"})
  void transmit(String identifier) {
    ByIdentifierRequestDto request = new ByIdentifierRequestDto();
    request.setIdentifier(identifier);
    request.setAuditWho(UUID.randomUUID().toString());
    transmitBusiness.process(request);
    assertStatus(request.getIdentifier(), BudgetStatus.TRANSMITTED);
  }

  @ParameterizedTest
  @ValueSource(strings = {"accept_when_transmitted"})
  void accept(String identifier) {
    ByIdentifierRequestDto request = new ByIdentifierRequestDto();
    request.setIdentifier(identifier);
    request.setAuditWho(UUID.randomUUID().toString());
    acceptBusiness.process(request);
    assertStatus(request.getIdentifier(), BudgetStatus.ACCEPTED);
  }

  @ParameterizedTest
  @ValueSource(strings = {"approve_when_accepted"})
  void approve(String identifier) {
    ByIdentifierRequestDto request = new ByIdentifierRequestDto();
    request.setIdentifier(identifier);
    request.setAuditWho(UUID.randomUUID().toString());
    approveBusiness.process(request);
    assertStatus(request.getIdentifier(), BudgetStatus.APPROVED);
  }

  @ParameterizedTest
  @ValueSource(strings = {"return_when_accepted"})
  void returnBack(String identifier) {
    BudgetReturnRequestDto request = new BudgetReturnRequestDto();
    request.setIdentifier(identifier);
    request.setReason("ma raison");
    request.setAuditWho(UUID.randomUUID().toString());
    returnBusiness.process(request);
    assertStatus(request.getIdentifier(), BudgetStatus.RETURNED);
  }

  @Test
  void readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.projection().addNames(BudgetDto.JSON_STATUS_AS_STRING, BudgetDto.JSON_TRANSMITABLE,
        BudgetDto.JSON_ACCEPTABLE, BudgetDto.JSON_APPROVABLE, BudgetDto.JSON_RETURNABLE);
    request.setAuditWho("christian");
    assertTrue(readManyBusiness.process(request).getCount() > 0);
  }

  @Test
  void update() {
    BudgetUpdateRequestDto request = new BudgetUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setAccountingPlanIdentifier("1");
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, Budget.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, Budget.ENTITY_NAME));
  }

  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }

  @Test
  void mapToDto_whenNotNull() {
    Budget instance = new Budget();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    BudgetDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    Budget instance = new Budget();
    instance.setIdentifier("1");
    BudgetDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }

  @Test
  void mapFromDto_whenAuditNull() {
    BudgetDto dto = new BudgetDto();
    dto.setIdentifier("1");
    Budget instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void mapFromDto_whenAuditNotNull() {
    BudgetDto dto = new BudgetDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Budget instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  @Test
  void instantiate() {
    assertNotNull(new BudgetAmount());
  }
  
  /* Department */

  @Test
  void department_mapToDto_whenNull() {
    assertNull(departmentMapper.mapToDto(null));
  }
  
  @Test
  void department_mapToDto_whenNotNull() {
    Department instance = new Department();
    instance.setIdentifier("1");
    DepartmentDto dto = departmentMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }
  
  @Test
  void department_mapFromDto_whenNull() {
    assertNull(departmentMapper.mapFromDto(null));
  }
  
  @Test
  void department_mapFromDto() {
    DepartmentDto dto = new DepartmentDto();
    dto.setIdentifier("1");
    Department instance = departmentMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }
  
  @Test
  void department_readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(1, departmentReadManyBusiness.process(request).getCount());
  }
  
  void assertStatus(String actIdentifier, BudgetStatus expectedStatus) {
    Budget budget = entityManager.find(Budget.class, actIdentifier);
    assertEquals(expectedStatus, budget.status);
  }
  
  void assertStatus(String actIdentifier, FundingStatus expectedStatus) {
    Funding funding = entityManager.find(Funding.class, actIdentifier);
    assertEquals(expectedStatus, funding.status);
  }
  
  /* FundingSource */

  @Test
  void fundingSource_getMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.projection().addNames(FundingSourceDto.JSON_AS_STRING);
    request.setAuditWho("christian");
    assertTrue(fundingSourceReadManyBusiness.process(request).getCount() > 0);
  }

  @Test
  void fundingSource_create() {
    FundingSourceCreateRequestDto request = new FundingSourceCreateRequestDto();
    request.setCode("c");
    request.setName(UUID.randomUUID().toString());
    request.setSchoolIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, FundingSource.ENTITY_NAME);
    fundingSourceCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, FundingSource.ENTITY_NAME));
  }

  @Test
  void fundingSource_mapToDto_whenNull() {
    assertNull(fundingSourceMapper.mapToDto(null));
  }

  @Test
  void fundingSource_mapToDto_whenNotNull() {
    FundingSource instance = new FundingSource();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    FundingSourceDto dto = fundingSourceMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void fundingSource_mapToDto_whenNotNullAndAuditNull() {
    FundingSource instance = new FundingSource();
    instance.setIdentifier("1");
    FundingSourceDto dto = fundingSourceMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void fundingSource_mapFromDto_whenNull() {
    assertNull(fundingSourceMapper.mapFromDto(null));
  }

  @Test
  void fundingSource_mapFromDto_whenAuditNull() {
    FundingSourceDto dto = new FundingSourceDto();
    dto.setIdentifier("1");
    FundingSource instance = fundingSourceMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void fundingSource_mapFromDto_whenAuditNotNull() {
    FundingSourceDto dto = new FundingSourceDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    FundingSource instance = fundingSourceMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  /* Funding */
  
  @Test
  void funding_create() {
    FundingCreateRequestDto request = new FundingCreateRequestDto();
    request.setBudgetIdentifier("1");
    request.setDepartmentIdentifier("1");
    request.setAccountingAccountIdentifier("1");
    request.setSourceIdentifier("1");
    request.setMonth(Month.FEBRUARY);
    request.setAmount(0L);
    request.setAuditWho("christian");
    long count = count(entityManager, Funding.ENTITY_NAME);
    fundingCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Funding.ENTITY_NAME));
  }

  @ParameterizedTest
  @ValueSource(strings = {"transmit_when_created"})
  void funding_transmit(String identifier) {
    ByIdentifierRequestDto request = new ByIdentifierRequestDto();
    request.setIdentifier(identifier);
    request.setAuditWho(UUID.randomUUID().toString());
    fundingTransmitBusiness.process(request);
    assertStatus(request.getIdentifier(), FundingStatus.TRANSMITTED);
  }
  
  @ParameterizedTest
  @ValueSource(strings = {"transmitbyfilter_when_created"})
  void funding_transmitByFilter(String identifier) {
    ByFilterRequestDto request = new ByFilterRequestDto();
    FundingFilter filter = new FundingFilter();
    filter.setIdentifier(identifier);
    request.setFilter(filter.toDto());
    request.setAuditWho(UUID.randomUUID().toString());
    fundingTransmitByFilterBusiness.process(request);
    assertStatus(identifier, FundingStatus.TRANSMITTED);
  }

  @ParameterizedTest
  @ValueSource(strings = {"accept_when_transmitted"})
  void funding_accept(String identifier) {
    ByIdentifierRequestDto request = new ByIdentifierRequestDto();
    request.setIdentifier(identifier);
    request.setAuditWho(UUID.randomUUID().toString());
    fundingAcceptBusiness.process(request);
    assertStatus(request.getIdentifier(), FundingStatus.ACCEPTED);
  }
  
  @ParameterizedTest
  @ValueSource(strings = {"acceptbyfilter_when_transmitted"})
  void funding_acceptByFilter(String identifier) {
    ByFilterRequestDto request = new ByFilterRequestDto();
    FundingFilter filter = new FundingFilter();
    filter.setIdentifier(identifier);
    request.setFilter(filter.toDto());
    request.setAuditWho(UUID.randomUUID().toString());
    fundingAcceptByFilterBusiness.process(request);
    assertStatus(identifier, FundingStatus.ACCEPTED);
  }

  @ParameterizedTest
  @ValueSource(strings = {"approve_when_accepted"})
  void funding_approve(String identifier) {
    ByIdentifierRequestDto request = new ByIdentifierRequestDto();
    request.setIdentifier(identifier);
    request.setAuditWho(UUID.randomUUID().toString());
    fundingApproveBusiness.process(request);
    assertStatus(request.getIdentifier(), FundingStatus.APPROVED);
  }
  
  @ParameterizedTest
  @ValueSource(strings = {"approvebyfilter_when_accepted"})
  void funding_approvedByFilter(String identifier) {
    ByFilterRequestDto request = new ByFilterRequestDto();
    FundingFilter filter = new FundingFilter();
    filter.setIdentifier(identifier);
    request.setFilter(filter.toDto());
    request.setAuditWho(UUID.randomUUID().toString());
    fundingApproveByFilterBusiness.process(request);
    assertStatus(identifier, FundingStatus.APPROVED);
  }

  @ParameterizedTest
  @ValueSource(strings = {"return_when_accepted"})
  void funding_return(String identifier) {
    FundingReturnRequestDto request = new FundingReturnRequestDto();
    request.setIdentifier(identifier);
    request.setReason("ma raison");
    request.setAuditWho(UUID.randomUUID().toString());
    fundingReturnBusiness.process(request);
    assertStatus(request.getIdentifier(), FundingStatus.RETURNED);
  }
  
  @ParameterizedTest
  @ValueSource(strings = {"returnbyfilter_when_accepted"})
  void funding_returnByFilter(String identifier) {
    ByFilterWithReasonRequestDto request = new ByFilterWithReasonRequestDto();
    FundingFilter filter = new FundingFilter();
    filter.setIdentifier(identifier);
    request.setFilter(filter.toDto());
    request.setReason("reason");
    request.setAuditWho(UUID.randomUUID().toString());
    fundingReturnByFilterBusiness.process(request);
    assertStatus(identifier, FundingStatus.RETURNED);
  }
  
  @Test
  void funding_readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.projection().addNames(FundingDto.JSON_MONTH_AS_STRING,
        FundingDto.JSON_AMOUNT_INPUTABLE);
    request.setAuditWho("christian");
    assertTrue(fundingReadManyBusiness.process(request).getCount() > 0);
  }

  @Test
  void funding_update() {
    FundingUpdateRequestDto request = new FundingUpdateRequestDto();
    request.setIdentifier("toupdate");

    request.setAuditWho("christian");
    long count = count(entityManager, Funding.ENTITY_NAME);
    fundingUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, Funding.ENTITY_NAME));
  }

  @Test
  void funding_updateAmount() {
    FundingUpdateAmountRequestDto request = new FundingUpdateAmountRequestDto();
    request.setIdentifier("toupdateamount");
    request.setAmount(1);
    request.setAuditWho("christian");
    long count = count(entityManager, Funding.ENTITY_NAME);
    fundingUpdateAmountBusiness.process(request);
    assertEquals(count, count(entityManager, Funding.ENTITY_NAME));
  }

  @Test
  void funding_mapToDto_whenNull() {
    assertNull(fundingMapper.mapToDto(null));
  }

  @Test
  void funding_mapToDto_whenNotNull() {
    Funding instance = new Funding();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    FundingDto dto = fundingMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void funding_mapToDto_whenNotNullAndAuditNull() {
    Funding instance = new Funding();
    instance.setIdentifier("1");
    FundingDto dto = fundingMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void funding_mapFromDto_whenNull() {
    assertNull(fundingMapper.mapFromDto(null));
  }

  @Test
  void funding_mapFromDto_whenAuditNull() {
    FundingDto dto = new FundingDto();
    dto.setIdentifier("1");
    Funding instance = fundingMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void funding_mapFromDto_whenAuditNotNull() {
    FundingDto dto = new FundingDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Funding instance = fundingMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  /* Execution */

  @Test
  void execution_create() {
    FundingExecutionCreateRequestDto request = new FundingExecutionCreateRequestDto();
    request.setFundingIdentifier("1");
    request.setAmount(1);
    request.setAuditWho("christian");
    assertCreateEntity(entityManager, FundingExecution.class,
        () -> executionCreateBusiness.process(request));
  }

  @Test
  void execution_readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.projection().addNames(FundingExecutionDto.JSON_FUNDING_AS_STRING);
    request.setAuditWho("christian");
    assertTrue(executionReadManyBusiness.process(request).getCount() > 0);
  }

  @Test
  void execution_update() {
    FundingExecutionUpdateRequestDto request = new FundingExecutionUpdateRequestDto();
    request.setIdentifier("toupdate");

    request.setAuditWho("christian");
    long count = count(entityManager, FundingExecution.ENTITY_NAME);
    executionUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, FundingExecution.ENTITY_NAME));
  }

  @Test
  void execution_mapToDto_whenNull() {
    assertNull(executionMapper.mapToDto(null));
  }

  @Test
  void execution_mapToDto_whenNotNull() {
    FundingExecution instance = new FundingExecution();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    FundingExecutionDto dto = executionMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void execution_mapToDto_whenNotNullAndAuditNull() {
    FundingExecution instance = new FundingExecution();
    instance.setIdentifier("1");
    FundingExecutionDto dto = executionMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void execution_mapFromDto_whenNull() {
    assertNull(executionMapper.mapFromDto(null));
  }

  @Test
  void execution_mapFromDto_whenAuditNull() {
    FundingExecutionDto dto = new FundingExecutionDto();
    dto.setIdentifier("1");
    FundingExecution instance = executionMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void execution_mapFromDto_whenAuditNotNull() {
    FundingExecutionDto dto = new FundingExecutionDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    FundingExecution instance = executionMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/budgetbusiness.sql");
    }
  }
}
