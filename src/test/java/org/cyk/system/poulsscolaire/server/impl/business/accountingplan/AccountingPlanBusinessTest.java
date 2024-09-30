package org.cyk.system.poulsscolaire.server.impl.business.accountingplan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanService.AccountingPlanCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlan;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AccountingPlanBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  AccountingPlanCreateBusiness createBusiness;

  @Inject
  AccountingPlanReadManyBusiness readManyBusiness;
  
  @Inject
  AccountingPlanReadOneBusiness readOneBusiness;
  
  @Inject
  AccountingPlanReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  AccountingPlanUpdateBusiness updateBusiness;
  
  @Inject
  AccountingPlanDeleteBusiness deleteBusiness;
  
  @Inject
  AccountingPlanMapper mapper;
  
  @Test
  void create() {
    AccountingPlanCreateRequestDto request = new AccountingPlanCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingPlan.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AccountingPlan.ENTITY_NAME));
  }
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    AccountingPlan instance = new AccountingPlan();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AccountingPlanDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    AccountingPlan instance = new AccountingPlan();
    instance.setIdentifier("1");
    AccountingPlanDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    AccountingPlanDto dto = new AccountingPlanDto();
    dto.setIdentifier("1");
    AccountingPlan instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    AccountingPlanDto dto = new AccountingPlanDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AccountingPlan instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
}
