package org.cyk.system.poulsscolaire.server.impl.business.accountingaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountDto;
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
  
  @Inject
  AccountingAccountMapper mapper;
  
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
  
  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    AccountingAccount instance = new AccountingAccount();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AccountingAccountDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    AccountingAccount instance = new AccountingAccount();
    instance.setIdentifier("1");
    AccountingAccountDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    AccountingAccountDto dto = new AccountingAccountDto();
    dto.setIdentifier("1");
    AccountingAccount instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    AccountingAccountDto dto = new AccountingAccountDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AccountingAccount instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/accountingaccountbusiness.sql");
    }
  }
}
