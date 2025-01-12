package org.cyk.system.poulsscolaire.server.impl.business.funding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.time.Month;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetStatus;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionService.FundingExecutionCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingExecutionService.FundingExecutionUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingUpdateAmountRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.FundingService.FundingUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionMapper;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fundingexecution.FundingExecutionValidator;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.cyk.system.poulsscolaire.server.impl.persistence.Funding;
import org.cyk.system.poulsscolaire.server.impl.persistence.FundingExecution;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(FundingBusinessTest.Profile.class)
class FundingBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  FundingCreateBusiness createBusiness;

  @Inject
  FundingReadManyBusiness readManyBusiness;

  @Inject
  FundingReadOneBusiness readOneBusiness;

  @Inject
  FundingReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  FundingUpdateBusiness updateBusiness;

  @Inject
  FundingUpdateAmountBusiness updateAmountBusiness;

  @Inject
  FundingDeleteBusiness deleteBusiness;

  @Inject
  FundingValidator validator;

  @Inject
  FundingMapper mapper;

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
    FundingCreateRequestDto request = new FundingCreateRequestDto();
    request.setBudgetIdentifier("1");
    request.setDepartmentIdentifier("1");
    request.setAccountingAccountIdentifier("1");
    request.setSourceIdentifier("1");
    request.setMonth(Month.FEBRUARY);
    request.setAmount(0L);
    request.setAuditWho("christian");
    long count = count(entityManager, Funding.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Funding.ENTITY_NAME));
  }

  @Test
  void readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.projection().addNames(FundingDto.JSON_MONTH_AS_STRING,
        FundingDto.JSON_AMOUNT_INPUTABLE);
    request.setAuditWho("christian");
    assertTrue(readManyBusiness.process(request).getCount() > 0);
  }

  @Test
  void update() {
    FundingUpdateRequestDto request = new FundingUpdateRequestDto();
    request.setIdentifier("toupdate");

    request.setAuditWho("christian");
    long count = count(entityManager, Funding.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, Funding.ENTITY_NAME));
  }

  @Test
  void updateAmount() {
    FundingUpdateAmountRequestDto request = new FundingUpdateAmountRequestDto();
    request.setIdentifier("toupdateamount");
    request.setAmount(1);
    request.setAuditWho("christian");
    long count = count(entityManager, Funding.ENTITY_NAME);
    updateAmountBusiness.process(request);
    assertEquals(count, count(entityManager, Funding.ENTITY_NAME));
  }

  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }

  @Test
  void mapToDto_whenNotNull() {
    Funding instance = new Funding();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    FundingDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    Funding instance = new Funding();
    instance.setIdentifier("1");
    FundingDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }

  @Test
  void mapFromDto_whenAuditNull() {
    FundingDto dto = new FundingDto();
    dto.setIdentifier("1");
    Funding instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void mapFromDto_whenAuditNotNull() {
    FundingDto dto = new FundingDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Funding instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  @Test
  void updateAmount_validate_whenBudgetStatusApproved() {
    FundingUpdateAmountRequestDto request = new FundingUpdateAmountRequestDto();
    Funding funding = new Funding();
    funding.budget = new Budget();
    funding.budget.status = BudgetStatus.APPROVED;
    StringList messages = new StringList();
    updateAmountBusiness.validate(request, messages, funding);
    assertNotNull(messages.getList());
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
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/fundingbusiness.sql");
    }
  }
}
