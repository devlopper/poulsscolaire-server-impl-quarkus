package org.cyk.system.poulsscolaire.server.impl.business.accountingoperation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.core.StringList;
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

  @Inject
  AccountingOperationValidator validator;

  @Test
  void computeName_whenBlank() {
    AccountingOperation accountingOperation = new AccountingOperation();
    accountingOperation.accountType = AccountingAccountType.EXPENDITURE;
    createBusiness.computeName(accountingOperation, 0);
    assertEquals("Dépense 0", accountingOperation.name);
  }

  @Test
  void computeName_whenNotBlank() {
    AccountingOperation accountingOperation = new AccountingOperation();
    accountingOperation.name = "myname";
    accountingOperation.accountType = AccountingAccountType.EXPENDITURE;
    createBusiness.computeName(accountingOperation, 0);
    assertEquals("myname", accountingOperation.name);
  }

  @Test
  void computeBeneficiary_whenBlank() {
    AccountingOperationCreateRequestDto request = new AccountingOperationCreateRequestDto();

    AccountingOperation accountingOperation = new AccountingOperation();
    accountingOperation.schoolIdentifier = "1";
    accountingOperation.accountType = AccountingAccountType.INCOME;
    createBusiness.computeBeneficiary(accountingOperation, request);
    assertEquals("CSP Cocody", accountingOperation.beneficiary);
  }

  @Test
  void computeBeneficiary_whenNotBlank() {
    AccountingOperationCreateRequestDto request = new AccountingOperationCreateRequestDto();
    request.setBeneficiary("b");
    AccountingOperation accountingOperation = new AccountingOperation();
    accountingOperation.accountType = AccountingAccountType.INCOME;
    createBusiness.computeBeneficiary(accountingOperation, request);
    assertEquals("b", accountingOperation.beneficiary);
  }

  @Test
  void validateBeneficiary_whenAccountTypeExpenditure() {
    assertTrue(
        validator.validateBeneficiary(null, AccountingAccountType.EXPENDITURE, new StringList()));
  }
  
  @Test
  void validateBeneficiary_whenAccountTypeIncome() {
    assertFalse(
        validator.validateBeneficiary(null, AccountingAccountType.INCOME, new StringList()));
  }

  @Test
  void create() {
    AccountingOperationCreateRequestDto request = new AccountingOperationCreateRequestDto();
    request.setSchoolIdentifier(UUID.randomUUID().toString());
    request.setPlanIdentifier("1");
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
    request.setPlanIdentifier("1");
    request.setBeneficiary(UUID.randomUUID().toString());
    request.setAccountType(AccountingAccountType.EXPENDITURE);
    request.setName(UUID.randomUUID().toString());
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
