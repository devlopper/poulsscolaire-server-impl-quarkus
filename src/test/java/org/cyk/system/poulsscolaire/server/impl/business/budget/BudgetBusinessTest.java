package org.cyk.system.poulsscolaire.server.impl.business.budget;

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
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService.BudgetCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.BudgetService.BudgetUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.Budget;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(BudgetBusinessTest.Profile.class)
class BudgetBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  BudgetCreateBusiness createBusiness;

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

  @Test
  void readMany() {
    GetManyRequestDto request = new GetManyRequestDto();
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

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/budgetbusiness.sql");
    }
  }
}
