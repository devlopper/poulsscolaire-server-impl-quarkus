package org.cyk.system.poulsscolaire.server.impl.business.accountingoperation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountType;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService.AccountingOperationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService.AccountingOperationUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperation;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AccountingOperationBusinessTest.Profile.class)
class AccountingOperationBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  AccountingOperationCreateBusiness createBusiness;

  @Inject
  AccountingOperationReadManyBusiness readManyBusiness;

  @Inject
  AccountingOperationReadOneBusiness readOneBusiness;

  @Inject
  AccountingOperationReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AccountingOperationUpdateBusiness updateBusiness;

  @Inject
  AccountingOperationDeleteBusiness deleteBusiness;

  @Test
  void create() {
    AccountingOperationCreateRequestDto request = new AccountingOperationCreateRequestDto();
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setBeneficiary(UUID.randomUUID().toString());
    request.setAccountType(AccountingAccountType.EXPENDITURE);
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingOperation.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AccountingOperation.ENTITY_NAME));
  }
  
  @Test
  void update() {
    AccountingOperationUpdateRequestDto request = new AccountingOperationUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setBeneficiary(UUID.randomUUID().toString());
    request.setAccountType(AccountingAccountType.EXPENDITURE);
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingOperation.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, AccountingOperation.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/accountingoperationbusiness.sql");
    }
  }
}
