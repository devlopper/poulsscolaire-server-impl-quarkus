package org.cyk.system.poulsscolaire.server.impl.business.schooling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.server.business.BusinessInputValidationException;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceFilter;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodDto;
import org.cyk.system.poulsscolaire.server.api.configuration.PeriodFilter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityService.SeniorityCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineDto;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineService.DeadlineCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeFilter;
import org.cyk.system.poulsscolaire.server.api.fee.FeeService.FeeCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeService.FeeUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchMapper;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceMapper;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineMapper;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.deadline.DeadlineUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeMapper;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.fee.FeeUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.period.PeriodReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.period.PeriodReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.period.PeriodReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityMapper;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.persistence.Amount;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstance;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstanceDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.Deadline;
import org.cyk.system.poulsscolaire.server.impl.persistence.DeadlineDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.Fee;
import org.cyk.system.poulsscolaire.server.impl.persistence.FeeDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.Period;
import org.cyk.system.poulsscolaire.server.impl.persistence.PeriodDynamicQuery;
import org.cyk.system.poulsscolaire.server.impl.persistence.Schooling;
import org.cyk.system.poulsscolaire.server.impl.persistence.Seniority;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(SchoolingBusinessTest.Profile.class)
class SchoolingBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  SchoolingCreateBusiness createBusiness;

  @Inject
  SchoolingReadManyBusiness readManyBusiness;

  @Inject
  SchoolingReadOneBusiness readOneBusiness;

  @Inject
  SchoolingReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  SchoolingUpdateBusiness updateBusiness;

  @Inject
  SchoolingDeleteBusiness deleteBusiness;

  @Inject
  SchoolingMapper mapper;
  
  /* Seniority */
  
  @Inject
  SeniorityCreateBusiness seniorityCreateBusiness;

  @Inject
  SeniorityReadManyBusiness seniorityReadManyBusiness;
  
  @Inject
  SeniorityReadOneBusiness seniorityReadOneBusiness;
  
  @Inject
  SeniorityReadByIdentifierBusiness seniorityReadByIdentifierBusiness;
  
  @Inject
  SeniorityUpdateBusiness seniorityUpdateBusiness;
  
  @Inject
  SeniorityDeleteBusiness seniorityDeleteBusiness;
  
  @Inject
  SeniorityMapper seniorityMapper;
  
  /* Branch */
  
  @Inject
  BranchReadManyBusiness branchReadManyBusiness;

  @Inject
  BranchReadOneBusiness branchReadOneBusiness;

  @Inject
  BranchReadByIdentifierBusiness branchReadByIdentifierBusiness;

  @Inject
  BranchMapper branchMapper;
  
  /* BranchInstance */
  
  @Inject
  BranchInstanceReadManyBusiness branchInstanceReadManyBusiness;

  @Inject
  BranchInstanceReadOneBusiness branchInstanceReadOneBusiness;

  @Inject
  BranchInstanceReadByIdentifierBusiness branchInstanceReadByIdentifierBusiness;

  @Inject
  BranchInstanceMapper branchInstanceMapper;

  @Inject
  BranchInstanceDynamicQuery branchInstanceDynamicQuery;

  DynamicQueryParameters<BranchInstance> branchInstanceDynamicQueryParameters =
      new DynamicQueryParameters<>();
  
  /* Fee */
  
  @Inject
  FeeCreateBusiness feeCreateBusiness;

  @Inject
  FeeReadManyBusiness feeReadManyBusiness;

  @Inject
  FeeReadOneBusiness feeReadOneBusiness;

  @Inject
  FeeReadByIdentifierBusiness feeReadByIdentifierBusiness;

  @Inject
  FeeUpdateBusiness feeUpdateBusiness;

  @Inject
  FeeDeleteBusiness feeDeleteBusiness;

  @Inject
  FeeMapper feeMapper;
  
  @Inject
  FeeDynamicQuery feeDynamicQuery;

  DynamicQueryParameters<Fee> feeDynamicQueryParameters = new DynamicQueryParameters<>();
  
  /* Deadline */
  
  @Inject
  DeadlineCreateBusiness deadlineCreateBusiness;

  @Inject
  DeadlineReadManyBusiness deadlineDeadlineReadManyBusiness;
  
  @Inject
  DeadlineReadOneBusiness deadlineReadOneBusiness;
  
  @Inject
  DeadlineReadByIdentifierBusiness deadlineReadByIdentifierBusiness;
  
  @Inject
  DeadlineUpdateBusiness deadlineUpdateBusiness;
  
  @Inject
  DeadlineDeleteBusiness deadlineDeleteBusiness;
  
  @Inject
  DeadlineMapper deadlineMapper;
  
  @Inject
  DeadlineDynamicQuery deadlineDynamicQuery;

  DynamicQueryParameters<Deadline> deadlineDynamicQueryParameters = new DynamicQueryParameters<>();
  
  /* Period */
  
  @Inject
  PeriodReadManyBusiness periodReadManyBusiness;

  @Inject
  PeriodReadOneBusiness periodReadOneBusiness;

  @Inject
  PeriodReadByIdentifierBusiness periodReadByIdentifierBusiness;

  @Inject
  PeriodDynamicQuery periodDynamicQuery;

  DynamicQueryParameters<Period> periodParameters = new DynamicQueryParameters<>();
  
  @Test
  void create() {
    SchoolingCreateRequestDto request = new SchoolingCreateRequestDto();
    request.setSchoolIdentifier("1");
    request.setBranchIdentifier("1");
    request.setPeriodIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, Schooling.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Schooling.ENTITY_NAME));
  }

  @Test
  void update() {
    SchoolingUpdateRequestDto request = new SchoolingUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setSchoolIdentifier("1");
    request.setBranchIdentifier("1");
    request.setPeriodIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, Schooling.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, Schooling.ENTITY_NAME));
  }
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    Schooling instance = new Schooling();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    SchoolingDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    Schooling instance = new Schooling();
    instance.setIdentifier("1");
    SchoolingDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    SchoolingDto dto = new SchoolingDto();
    dto.setIdentifier("1");
    Schooling instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    SchoolingDto dto = new SchoolingDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Schooling instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  /* Seniority */
  
  @Test
  void seniority_create() {
    SeniorityCreateRequestDto request = new SeniorityCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, Seniority.ENTITY_NAME);
    seniorityCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Seniority.ENTITY_NAME));
  }
  
  @Test
  void seniority_mapToDto_whenNull() {
    assertNull(seniorityMapper.mapToDto(null));
  }
  
  @Test
  void seniority_mapToDto_whenNotNull() {
    Seniority instance = new Seniority();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    SeniorityDto dto = seniorityMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void seniority_mapToDto_whenNotNullAndAuditNull() {
    Seniority instance = new Seniority();
    instance.setIdentifier("1");
    SeniorityDto dto = seniorityMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void seniority_mapFromDto_whenNull() {
    assertNull(seniorityMapper.mapFromDto(null));
  }
  
  @Test
  void seniority_mapFromDto_whenAuditNull() {
    SeniorityDto dto = new SeniorityDto();
    dto.setIdentifier("1");
    Seniority instance = seniorityMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void seniority_mapFromDto_whenAuditNotNull() {
    SeniorityDto dto = new SeniorityDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Seniority instance = seniorityMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  /* Branch */
  
  @Test
  void branch_readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(7, branchReadManyBusiness.process(request).getCount());
  }
  
  @Test
  void branch_mapToDto_whenNull() {
    assertNull(branchMapper.mapToDto(null));
  }
  
  @Test
  void branch_mapToDto_whenNotNull() {
    Branch instance = new Branch();
    instance.setIdentifier("1");
    BranchDto dto = branchMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }
  
  @Test
  void branch_mapFromDto_whenNull() {
    assertNull(branchMapper.mapFromDto(null));
  }
  
  @Test
  void branch_mapFromDto() {
    BranchDto dto = new BranchDto();
    dto.setIdentifier("1");
    Branch instance = branchMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }
  
  /* BranchInstance */
  
  @Test
  void branchInstance_readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertEquals(8, branchInstanceReadManyBusiness.process(request).getCount());
  }

  @Test
  void branchInstance_mapToDto_whenNull() {
    assertNull(branchInstanceMapper.mapToDto(null));
  }

  @Test
  void branchInstance_mapToDto_whenNotNull() {
    BranchInstance instance = new BranchInstance();
    instance.setIdentifier("1");
    BranchInstanceDto dto = branchInstanceMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
  }

  @Test
  void branchInstance_mapFromDto_whenNull() {
    assertNull(branchInstanceMapper.mapFromDto(null));
  }

  @Test
  void branchInstance_mapFromDto() {
    BranchInstanceDto dto = new BranchInstanceDto();
    dto.setIdentifier("1");
    BranchInstance instance = branchInstanceMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
  }

  @ParameterizedTest
  @CsvSource(value = {"1,1,1", "2,2,3:4:5", "3,3,"})
  void branchInstance_getMany_whenSchoolIdentifierWhenBranchIdentifier(String schoolIdentifier,
      String branchIdentifier, String expected) {
    BranchInstanceFilter filter = new BranchInstanceFilter();
    filter.setSchoolIdentifier(schoolIdentifier);
    filter.setBranchIdentifier(branchIdentifier);
    branchInstanceDynamicQueryParameters.setFilter(filter.toDto());
    branchInstanceDynamicQueryParameters.projection().addNames(BranchInstanceDto.JSON_IDENTIFIER);
    List<BranchInstance> instances =
        branchInstanceDynamicQuery.getMany(branchInstanceDynamicQueryParameters);
    assertNotNull(instances);
    if (Core.isStringBlank(expected)) {
      assertEquals(0, instances.size());
    } else {
      assertLinesMatch(List.of(expected.split(":")),
          instances.stream().map(i -> i.getIdentifier()).toList());
    }
  }

  @ParameterizedTest
  @CsvSource(value = {"1,6", "2,7:8", "3,"})
  void branchInstance_getMany_whenSchoolingIdentifier(String schoolingIdentifier, String expected) {
    BranchInstanceFilter filter = new BranchInstanceFilter();
    filter.setSchoolingIdentifier(schoolingIdentifier);
    branchInstanceDynamicQueryParameters.setFilter(filter.toDto());
    branchInstanceDynamicQueryParameters.projection().addNames(BranchInstanceDto.JSON_IDENTIFIER);
    List<BranchInstance> instances =
        branchInstanceDynamicQuery.getMany(branchInstanceDynamicQueryParameters);
    assertNotNull(instances);
    if (Core.isStringBlank(expected)) {
      assertEquals(0, instances.size());
    } else {
      assertLinesMatch(List.of(expected.split(":")),
          instances.stream().map(i -> i.getIdentifier()).toList());
    }
  }
  
  /* Fee */
  
  @Test
  void fee_buildQuery_whenProjectionSchoolingSchoolAsString() {
    feeDynamicQueryParameters.projection().addNames(FeeDto.JSON_SCHOOLING_SCHOOL_AS_STRING);
    assertEquals(
        "SELECT school.name FROM Fee t "
            + "LEFT JOIN School school ON school.identifier = t.schooling.schoolIdentifier "
            + "ORDER BY t.amount.paymentOrderNumber ASC,t.category.name ASC",
        feeDynamicQuery.buildQueryString(feeDynamicQueryParameters));
  }

  @ParameterizedTest
  @CsvSource(value = {"1,1,1,100,0", "2,1,1,500,75"})
  void fee_getOne_whenSumAmountAndRegistration_whenFilterSchoolingAssignlentTypeSeniority(
      String schoolingIdentifier, String assignmenttypeIdentifier, String seniorityIdentifier,
      String expectedAmountSum, String expectedRegistrationSum) {
    feeDynamicQueryParameters.setResultMode(ResultMode.ONE);
    feeDynamicQueryParameters.projection().addNames(FeeDto.JSON_AMOUNT_VALUE_SUM_AS_STRING,
        FeeDto.JSON_AMOUNT_REGISTRATION_SUM_AS_STRING);
    feeDynamicQueryParameters.filter().addCriteria(FeeFilter.JSON_SCHOOLING_IDENTIFIER,
        schoolingIdentifier);
    feeDynamicQueryParameters.filter().addCriteria(FeeFilter.JSON_ASSIGNMENT_TYPE_IDENTIFIER,
        assignmenttypeIdentifier);
    feeDynamicQueryParameters.filter().addCriteria(FeeFilter.JSON_SENIORITY_IDENTIFIER,
        seniorityIdentifier);
    Fee fee = feeDynamicQuery.getOne(feeDynamicQueryParameters);
    assertEquals(expectedAmountSum, fee.amountValueSumAsString);
    assertEquals(expectedRegistrationSum, fee.amountRegistrationSumAsString);
  }
  
  @Test
  void fee_create() {
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
    feeCreateBusiness.process(request);
    assertEquals(feeCount + 1, count(entityManager, Fee.ENTITY_NAME));
    assertEquals(amountCount + 1, count(entityManager, Amount.ENTITY_NAME));
  }
  
  @Test
  void fee_create_whenValueZero() {
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
    assertThrows(BusinessInputValidationException.class, () -> feeCreateBusiness.process(request));
    assertEquals(feeCount, count(entityManager, Fee.ENTITY_NAME));
    assertEquals(amountCount, count(entityManager, Amount.ENTITY_NAME));
  }
  
  @Test
  void fee_create_whenRegistrationValuePartGreaterThanValue() {
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
    assertThrows(BusinessInputValidationException.class, () -> feeCreateBusiness.process(request));
    assertEquals(feeCount, count(entityManager, Fee.ENTITY_NAME));
    assertEquals(amountCount, count(entityManager, Amount.ENTITY_NAME));
  }
  
  @Test
  void fee_update() {
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
    feeUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, Fee.ENTITY_NAME));
  }
  
  @Test
  void fee_updateOptional_whenOptionalTrue() {
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
    feeUpdateBusiness.process(request);
    Fee fee = entityManager.find(Fee.class, request.getIdentifier());
    assertFalse(fee.amount.optional);
    assertNotNull(fee.amount.paymentOrderNumber);
  }
  
  @Test
  void fee_mapToDto_whenNull() {
    assertNull(feeMapper.mapToDto(null));
  }
  
  @Test
  void fee_mapToDto_whenNotNull() {
    Fee instance = new Fee();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    FeeDto dto = feeMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void fee_mapToDto_whenNotNullAndAuditNull() {
    Fee instance = new Fee();
    instance.setIdentifier("1");
    FeeDto dto = feeMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void fee_mapFromDto_whenNull() {
    assertNull(feeMapper.mapFromDto(null));
  }
  
  @Test
  void fee_mapFromDto_whenAuditNull() {
    FeeDto dto = new FeeDto();
    dto.setIdentifier("1");
    Fee instance = feeMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void fee_mapFromDto_whenAuditNotNull() {
    FeeDto dto = new FeeDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Fee instance = feeMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  /* Deadline */
  
  @Test
  void deadline_getMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.projection().addNames(DeadlineDto.JSON_AS_STRING);
    request.setAuditWho("christian");
    assertTrue(deadlineDeadlineReadManyBusiness.process(request).getCount() > 0);
  }
  
  @Test
  void deadline_create() {
    DeadlineCreateRequestDto request = new DeadlineCreateRequestDto();
    request.setName(UUID.randomUUID().toString());
    request.setGroupIdentifier("1");
    request.setSchoolIdentifier("1");
    request.setDate(LocalDateTime.now());
    request.setAuditWho("christian");
    long count = count(entityManager, Deadline.ENTITY_NAME);
    deadlineCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, Deadline.ENTITY_NAME));
  }
  
  @Test
  void deadline_mapToDto_whenNull() {
    assertNull(deadlineMapper.mapToDto(null));
  }
  
  @Test
  void deadline_mapToDto_whenNotNull() {
    Deadline instance = new Deadline();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    DeadlineDto dto = deadlineMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void deadline_mapToDto_whenNotNullAndAuditNull() {
    Deadline instance = new Deadline();
    instance.setIdentifier("1");
    DeadlineDto dto = deadlineMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void deadline_mapFromDto_whenNull() {
    assertNull(deadlineMapper.mapFromDto(null));
  }
  
  @Test
  void deadline_mapFromDto_whenAuditNull() {
    DeadlineDto dto = new DeadlineDto();
    dto.setIdentifier("1");
    Deadline instance = deadlineMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void deadline_mapFromDto_whenAuditNotNull() {
    DeadlineDto dto = new DeadlineDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    Deadline instance = deadlineMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  /* Period */
  
  @ParameterizedTest
  @CsvSource(value = {"1,true,3", "1,false,1:2", "2,true,4", "2,false,1:2:3", "3,true,3:4",
      "3,false,1:2", "4,true,", "4,false,"})
  void period_getMany_whenSchoolIdentifier_whenOpened(String schoolIdentifier, boolean opened,
      String expected) {
    PeriodFilter filter = new PeriodFilter();
    filter.setSchoolIdentifier(schoolIdentifier);
    filter.setOpened(opened);
    periodParameters.setFilter(filter.toDto());
    periodParameters.projection().addNames(PeriodDto.JSON_IDENTIFIER);
    List<Period> periods = periodDynamicQuery.getMany(periodParameters);
    assertNotNull(periods);
    if (Core.isStringBlank(expected)) {
      assertEquals(0, periods.size());
    } else {
      assertLinesMatch(List.of(expected.split(":")),
          periods.stream().map(i -> i.getIdentifier()).toList());
    }

  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/schoolingbusiness.sql");
    }
  }
}
