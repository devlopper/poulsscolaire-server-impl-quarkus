package org.cyk.system.poulsscolaire.server.impl.business.schooling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.core.Core;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceFilter;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingService.SchoolingUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SeniorityService.SeniorityCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchMapper;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branch.BranchReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceMapper;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.branchinstance.BranchInstanceReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityMapper;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.seniority.SeniorityUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.persistence.Branch;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstance;
import org.cyk.system.poulsscolaire.server.impl.persistence.BranchInstanceDynamicQuery;
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
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/schoolingbusiness.sql");
    }
  }
}
