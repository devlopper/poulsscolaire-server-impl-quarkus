package org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountService.AccountingOperationAccountCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountService.AccountingOperationAccountUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperation;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlan;
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

  @Inject
  AccountingOperationAccountValidator validator;

  @Inject
  AccountingOperationAccountMapper mapper;
  
  @Test
  void validateAccount_whenOperationNull() {
    assertFalse(validator.validateAccount(null, null, new StringList()));
  }

  @Test
  void validateAccount_whenAccountnNull() {
    assertFalse(validator.validateAccount(new AccountingOperation(), null, new StringList()));
  }

  @Test
  void validateAccount_whenDifferentPlan() {
    AccountingPlan plan1 = new AccountingPlan();
    plan1.generateIdentifier();
    AccountingOperation operation = new AccountingOperation();
    operation.plan = plan1;

    AccountingPlan plan2 = new AccountingPlan();
    plan2.generateIdentifier();
    AccountingAccount account = new AccountingAccount();
    account.plan = plan2;

    assertTrue(validator.validateAccount(operation, account, new StringList()));
  }
  
  @Test
  void validateAccount_whenSamePlan() {
    AccountingPlan plan1 = new AccountingPlan();
    plan1.identifier = "1";
    AccountingOperation operation = new AccountingOperation();
    operation.plan = plan1;

    AccountingPlan plan2 = new AccountingPlan();
    plan2.identifier = "1";
    AccountingAccount account = new AccountingAccount();
    account.plan = plan2;

    assertFalse(validator.validateAccount(operation, account, new StringList()));
  }

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
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    AccountingOperationAccount instance = new AccountingOperationAccount();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AccountingOperationAccountDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    AccountingOperationAccount instance = new AccountingOperationAccount();
    instance.setIdentifier("1");
    AccountingOperationAccountDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    AccountingOperationAccountDto dto = new AccountingOperationAccountDto();
    dto.setIdentifier("1");
    AccountingOperationAccount instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    AccountingOperationAccountDto dto = new AccountingOperationAccountDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AccountingOperationAccount instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/accountingoperationaccountbusiness.sql");
    }
  }
}
