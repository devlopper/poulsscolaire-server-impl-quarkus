package org.cyk.system.poulsscolaire.server.impl.business.accountingplan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.UUID;
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
}
