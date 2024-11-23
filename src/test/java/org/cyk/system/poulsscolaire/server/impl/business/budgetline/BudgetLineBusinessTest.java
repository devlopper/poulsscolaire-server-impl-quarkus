package org.cyk.system.poulsscolaire.server.impl.business.budgetline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.GetManyRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineService.BudgetLineCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetLineService.BudgetLineUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.BudgetLine;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(BudgetLineBusinessTest.Profile.class)
class BudgetLineBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  BudgetLineCreateBusiness createBusiness;

  @Inject
  BudgetLineReadManyBusiness readManyBusiness;

  @Inject
  BudgetLineReadOneBusiness readOneBusiness;

  @Inject
  BudgetLineReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  BudgetLineUpdateBusiness updateBusiness;

  @Inject
  BudgetLineDeleteBusiness deleteBusiness;

  @Inject
  BudgetLineValidator validator;

  @Inject
  BudgetLineMapper mapper;

  @Test
  void create() {
    BudgetLineCreateRequestDto request = new BudgetLineCreateRequestDto();
    request.setBudgetIdentifier("1");
    request.setDepartmentIdentifier("1");
    request.setAccountingAccountIdentifier("1");
    request.setMonthIndex(1);
    request.setAmount(0L);
    request.setAuditWho("christian");
    long count = count(entityManager, BudgetLine.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, BudgetLine.ENTITY_NAME));
  }

  @Test
  void readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
    request.setAuditWho("christian");
    assertTrue(readManyBusiness.process(request).getCount() > 0);
  }

  @Test
  void update() {
    BudgetLineUpdateRequestDto request = new BudgetLineUpdateRequestDto();
    request.setIdentifier("toupdate");
    
    request.setAuditWho("christian");
    long count = count(entityManager, BudgetLine.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, BudgetLine.ENTITY_NAME));
  }

  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }

  @Test
  void mapToDto_whenNotNull() {
    BudgetLine instance = new BudgetLine();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    BudgetLineDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    BudgetLine instance = new BudgetLine();
    instance.setIdentifier("1");
    BudgetLineDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }

  @Test
  void mapFromDto_whenAuditNull() {
    BudgetLineDto dto = new BudgetLineDto();
    dto.setIdentifier("1");
    BudgetLine instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void mapFromDto_whenAuditNotNull() {
    BudgetLineDto dto = new BudgetLineDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    BudgetLine instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/budgetlinebusiness.sql");
    }
  }
}
