package org.cyk.system.poulsscolaire.server.impl.business.accountingaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountService.AccountingAccountCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountService.AccountingAccountUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountType;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AccountingAccountBusinessTest.Profile.class)
class AccountingAccountBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  AccountingAccountCreateBusiness createBusiness;

  @Inject
  AccountingAccountReadManyBusiness readManyBusiness;
  
  @Inject
  AccountingAccountReadOneBusiness readOneBusiness;
  
  @Inject
  AccountingAccountReadByIdentifierBusiness readByIdentifierBusiness;
  
  @Inject
  AccountingAccountUpdateBusiness updateBusiness;
  
  @Inject
  AccountingAccountDeleteBusiness deleteBusiness;
  
  @Test
  void create() {
    AccountingAccountCreateRequestDto request = new AccountingAccountCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setType(AccountingAccountType.EXPENDITURE);
    request.setPlanIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingAccount.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AccountingAccount.ENTITY_NAME));
  }
  
  @Test
  void update() {
    AccountingAccountUpdateRequestDto request = new AccountingAccountUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setType(AccountingAccountType.EXPENDITURE);
    request.setPlanIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingAccount.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, AccountingAccount.ENTITY_NAME));
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/accountingaccountbusiness.sql");
    }
  }
}
