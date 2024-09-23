package org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountService.AccountingOperationAccountCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountService.AccountingOperationAccountUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation.AccountingOperationAccountCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation.AccountingOperationAccountDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation.AccountingOperationAccountReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation.AccountingOperationAccountReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation.AccountingOperationAccountReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountoperation.AccountingOperationAccountUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccount;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AccountingOperationAccountBusinessTest.Profile.class)
class AccountingOperationAccountBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  AccountingOperationAccountCreateBusiness createBusiness;

  @Inject
  AccountingOperationAccountReadManyBusiness readManyBusiness;

  @Inject
  AccountingOperationAccountReadOneBusiness readOneBusiness;

  @Inject
  AccountingOperationAccountReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AccountingOperationAccountUpdateBusiness updateBusiness;

  @Inject
  AccountingOperationAccountDeleteBusiness deleteBusiness;

  @Test
  void create() {
    AccountingOperationAccountCreateRequestDto request =
        new AccountingOperationAccountCreateRequestDto();
    request.setName(UUID.randomUUID().toString());
    request.setOperationIdentifier("1");
    request.setAccountIdentifier("1");
    request.setAmount(1);
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingOperationAccount.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AccountingOperationAccount.ENTITY_NAME));
  }

  @Test
  void update() {
    AccountingOperationAccountUpdateRequestDto request =
        new AccountingOperationAccountUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setName(UUID.randomUUID().toString());
    request.setOperationIdentifier("1");
    request.setAccountIdentifier("2");
    request.setAmount(1);
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingOperationAccount.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, AccountingOperationAccount.ENTITY_NAME));
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/accountingoperationaccountbusiness.sql");
    }
  }
}
