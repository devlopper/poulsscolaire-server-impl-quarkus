package org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTestProfile;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class AccountingOperationAccountBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  AccountingOperationAccountCreateBusiness accountingOperationAccountCreateBusiness;

  @Inject
  AccountingOperationAccountReadManyBusiness accountingOperationAccountReadManyBusiness;

  @Inject
  AccountingOperationAccountReadOneBusiness accountingOperationAccountReadOneBusiness;

  @Inject
  AccountingOperationAccountReadByIdentifierBusiness
      accountingOperationAccountReadByIdentifierBusiness;

  @Inject
  AccountingOperationAccountUpdateBusiness accountingOperationAccountUpdateBusiness;

  @Inject
  AccountingOperationAccountDeleteBusiness accountingOperationAccountDeleteBusiness;

  @Inject
  AccountingOperationAccountValidator accountingOperationAccountValidator;

  @Inject
  AccountingOperationAccountMapper accountingOperationAccountMapper;

  @Test
  void accountingOperationAccount_validateAccount_whenOperationNull() {
    assertFalse(accountingOperationAccountValidator.validateAccount(null, null, new StringList()));
  }

  @Test
  void accountingOperationAccount_validateAccount_whenAccountnNull() {
    assertFalse(accountingOperationAccountValidator.validateAccount(new AccountingOperation(), null,
        new StringList()));
  }

  @Test
  void accountingOperationAccount_validateAccount_whenDifferentPlan() {
    AccountingPlan plan1 = new AccountingPlan();
    plan1.generateIdentifier();
    AccountingOperation operation = new AccountingOperation();
    operation.plan = plan1;

    AccountingPlan plan2 = new AccountingPlan();
    plan2.generateIdentifier();
    AccountingAccount account = new AccountingAccount();
    account.plan = plan2;

    assertTrue(
        accountingOperationAccountValidator.validateAccount(operation, account, new StringList()));
  }

  @Test
  void accountingOperationAccount_validateAccount_whenSamePlan() {
    AccountingPlan plan1 = new AccountingPlan();
    plan1.identifier = "1";
    AccountingOperation operation = new AccountingOperation();
    operation.plan = plan1;

    AccountingPlan plan2 = new AccountingPlan();
    plan2.identifier = "1";
    AccountingAccount account = new AccountingAccount();
    account.plan = plan2;

    assertFalse(
        accountingOperationAccountValidator.validateAccount(operation, account, new StringList()));
  }

  @Test
  void accountingOperationAccount_create() {
    AccountingOperationAccountCreateRequestDto request =
        new AccountingOperationAccountCreateRequestDto();
    request.setName(UUID.randomUUID().toString());
    request.setOperationIdentifier("1");
    request.setAccountIdentifier("1");
    request.setAmount(1);
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingOperationAccount.ENTITY_NAME);
    accountingOperationAccountCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AccountingOperationAccount.ENTITY_NAME));
  }

  @Test
  void accountingOperationAccount_update() {
    AccountingOperationAccountUpdateRequestDto request =
        new AccountingOperationAccountUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setName(UUID.randomUUID().toString());
    request.setOperationIdentifier("1");
    request.setAccountIdentifier("2");
    request.setAmount(1);
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingOperationAccount.ENTITY_NAME);
    accountingOperationAccountUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, AccountingOperationAccount.ENTITY_NAME));
  }

  @Test
  void accountingOperationAccount_mapToDto_whenNull() {
    assertNull(accountingOperationAccountMapper.mapToDto(null));
  }

  @Test
  void accountingOperationAccount_mapToDto_whenNotNull() {
    AccountingOperationAccount instance = new AccountingOperationAccount();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AccountingOperationAccountDto dto = accountingOperationAccountMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }

  @Test
  void accountingOperationAccount_mapToDto_whenNotNullAndAuditNull() {
    AccountingOperationAccount instance = new AccountingOperationAccount();
    instance.setIdentifier("1");
    AccountingOperationAccountDto dto = accountingOperationAccountMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }

  @Test
  void accountingOperationAccount_mapFromDto_whenNull() {
    assertNull(accountingOperationAccountMapper.mapFromDto(null));
  }

  @Test
  void accountingOperationAccount_mapFromDto_whenAuditNull() {
    AccountingOperationAccountDto dto = new AccountingOperationAccountDto();
    dto.setIdentifier("1");
    AccountingOperationAccount instance = accountingOperationAccountMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }

  @Test
  void accountingOperationAccount_mapFromDto_whenAuditNotNull() {
    AccountingOperationAccountDto dto = new AccountingOperationAccountDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AccountingOperationAccount instance = accountingOperationAccountMapper.mapFromDto(dto);
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
