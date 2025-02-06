package org.cyk.system.poulsscolaire.server.impl.business.accountingoperation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.core.StringList;
import ci.gouv.dgbf.extension.server.persistence.entity.embeddable.Audit;
import ci.gouv.dgbf.extension.server.service.api.entity.AuditDto;
import ci.gouv.dgbf.extension.server.service.api.request.ByIdentifierRequestDto;
import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolService.AccountingAccountSchoolCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolService.AccountingAccountSchoolUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountService.AccountingAccountCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountService.AccountingAccountUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountType;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountService.AccountingOperationAccountCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationAccountService.AccountingOperationAccountUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService.AccountingOperationCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationService.AccountingOperationUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanService.AccountingPlanCreateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountMapper;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccount.AccountingAccountUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolMapper;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool.AccountingAccountSchoolUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountMapper;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingoperationaccount.AccountingOperationAccountValidator;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanCreateBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanDeleteBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanMapper;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanReadByIdentifierBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanReadManyBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanReadOneBusiness;
import org.cyk.system.poulsscolaire.server.impl.business.accountingplan.AccountingPlanUpdateBusiness;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchool;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperation;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingOperationAccount;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingPlan;
import org.cyk.system.poulsscolaire.server.impl.persistence.Payment;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AccountingOperationBusinessTest.Profile.class)
class AccountingOperationBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  /* AccountingPlan */
  
  @Inject
  AccountingPlanCreateBusiness accountingPlanCreateBusiness;

  @Inject
  AccountingPlanReadManyBusiness accountingPlanReadManyBusiness;
  
  @Inject
  AccountingPlanReadOneBusiness accountingPlanReadOneBusiness;
  
  @Inject
  AccountingPlanReadByIdentifierBusiness accountingPlanReadByIdentifierBusiness;
  
  @Inject
  AccountingPlanUpdateBusiness accountingPlanUpdateBusiness;
  
  @Inject
  AccountingPlanDeleteBusiness accountingPlanDeleteBusiness;
  
  @Inject
  AccountingPlanMapper accountingPlanMapper;
  
  /* AccountingAccount */
  
  @Inject
  AccountingAccountCreateBusiness accountingAccountCreateBusiness;

  @Inject
  AccountingAccountReadManyBusiness accountingAccountReadManyBusiness;
  
  @Inject
  AccountingAccountReadOneBusiness accountingAccountReadOneBusiness;
  
  @Inject
  AccountingAccountReadByIdentifierBusiness accountingAccountReadByIdentifierBusiness;
  
  @Inject
  AccountingAccountUpdateBusiness accountingAccountUpdateBusiness;
  
  @Inject
  AccountingAccountDeleteBusiness accountingAccountDeleteBusiness;
  
  @Inject
  AccountingAccountMapper accountingAccountMapper;
  
  /* AccountingAccountSchool */
  
  @Inject
  AccountingAccountSchoolCreateBusiness accountingAccountSchoolCreateBusiness;

  @Inject
  AccountingAccountSchoolReadManyBusiness accountingAccountSchoolReadManyBusiness;

  @Inject
  AccountingAccountSchoolReadOneBusiness accountingAccountSchoolReadOneBusiness;

  @Inject
  AccountingAccountSchoolReadByIdentifierBusiness accountingAccountSchoolReadByIdentifierBusiness;

  @Inject
  AccountingAccountSchoolUpdateBusiness accountingAccountSchoolUpdateBusiness;

  @Inject
  AccountingAccountSchoolDeleteBusiness accountingAccountSchoolDeleteBusiness;

  @Inject
  AccountingAccountSchoolMapper accountingAccountSchoolMapper;
  
  /* AccountingOperation */
  
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
  AccountingOperationCancelBusiness cancelBusiness;

  @Inject
  AccountingOperationDeleteBusiness deleteBusiness;
  
  @Inject
  AccountingOperationValidator validator;

  @Inject
  AccountingOperationMapper mapper;
  
  /* AccountingOperationAccount */
  
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
  
  /* AccountingPlan */
  
  @Test
  void accountingPlan_create() {
    AccountingPlanCreateRequestDto request = new AccountingPlanCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingPlan.ENTITY_NAME);
    accountingPlanCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AccountingPlan.ENTITY_NAME));
  }
  
  @Test
  void accountingPlan_mapToDto_whenNull() {
    assertNull(accountingPlanMapper.mapToDto(null));
  }
  
  @Test
  void accountingPlan_mapToDto_whenNotNull() {
    AccountingPlan instance = new AccountingPlan();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AccountingPlanDto dto = accountingPlanMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void accountingPlan_mapToDto_whenNotNullAndAuditNull() {
    AccountingPlan instance = new AccountingPlan();
    instance.setIdentifier("1");
    AccountingPlanDto dto = accountingPlanMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void accountingPlan_mapFromDto_whenNull() {
    assertNull(accountingPlanMapper.mapFromDto(null));
  }
  
  @Test
  void accountingPlan_mapFromDto_whenAuditNull() {
    AccountingPlanDto dto = new AccountingPlanDto();
    dto.setIdentifier("1");
    AccountingPlan instance = accountingPlanMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void accountingPlan_mapFromDto_whenAuditNotNull() {
    AccountingPlanDto dto = new AccountingPlanDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AccountingPlan instance = accountingPlanMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  /* AccountingAccount */
  
  @Test
  void accountingAccount_create() {
    AccountingAccountCreateRequestDto request = new AccountingAccountCreateRequestDto();
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setType(AccountingAccountType.EXPENDITURE);
    request.setPlanIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingAccount.ENTITY_NAME);
    accountingAccountCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AccountingAccount.ENTITY_NAME));
  }
  
  @Test
  void accountingAccount_update() {
    AccountingAccountUpdateRequestDto request = new AccountingAccountUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setCode(UUID.randomUUID().toString());
    request.setName(UUID.randomUUID().toString());
    request.setType(AccountingAccountType.EXPENDITURE);
    request.setPlanIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingAccount.ENTITY_NAME);
    accountingAccountUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, AccountingAccount.ENTITY_NAME));
  }
  
  @Test
  void accountingAccount_mapToDto_whenNull() {
    assertNull(accountingAccountMapper.mapToDto(null));
  }
  
  @Test
  void accountingAccount_mapToDto_whenNotNull() {
    AccountingAccount instance = new AccountingAccount();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AccountingAccountDto dto = accountingAccountMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void accountingAccount_mapToDto_whenNotNullAndAuditNull() {
    AccountingAccount instance = new AccountingAccount();
    instance.setIdentifier("1");
    AccountingAccountDto dto = accountingAccountMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void accountingAccount_mapFromDto_whenNull() {
    assertNull(accountingAccountMapper.mapFromDto(null));
  }
  
  @Test
  void accountingAccount_mapFromDto_whenAuditNull() {
    AccountingAccountDto dto = new AccountingAccountDto();
    dto.setIdentifier("1");
    AccountingAccount instance = accountingAccountMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void accountingAccount_mapFromDto_whenAuditNotNull() {
    AccountingAccountDto dto = new AccountingAccountDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AccountingAccount instance = accountingAccountMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  /* AccountingAccountSchool */
  
  @Test
  void accountingAccountSchool_create() {
    AccountingAccountSchoolCreateRequestDto request = new AccountingAccountSchoolCreateRequestDto();
    request.setAccountIdentifier("1");
    request.setSchoolIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingAccountSchool.ENTITY_NAME);
    accountingAccountSchoolCreateBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AccountingAccountSchool.ENTITY_NAME));
  }

  @Test
  void accountingAccountSchool_update() {
    AccountingAccountSchoolUpdateRequestDto request = new AccountingAccountSchoolUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setAccountIdentifier("1");
    request.setSchoolIdentifier("2");
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingAccountSchool.ENTITY_NAME);
    accountingAccountSchoolUpdateBusiness.process(request);
    assertEquals(count, count(entityManager, AccountingAccountSchool.ENTITY_NAME));
  }
  
  @Test
  void accountingAccountSchool_mapToDto_whenNull() {
    assertNull(accountingAccountSchoolMapper.mapToDto(null));
  }
  
  @Test
  void accountingAccountSchool_mapToDto_whenNotNull() {
    AccountingAccountSchool instance = new AccountingAccountSchool();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AccountingAccountSchoolDto dto = accountingAccountSchoolMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void accountingAccountSchool_mapToDto_whenNotNullAndAuditNull() {
    AccountingAccountSchool instance = new AccountingAccountSchool();
    instance.setIdentifier("1");
    AccountingAccountSchoolDto dto = accountingAccountSchoolMapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void accountingAccountSchool_mapFromDto_whenNull() {
    assertNull(accountingAccountSchoolMapper.mapFromDto(null));
  }
  
  @Test
  void accountingAccountSchool_mapFromDto_whenAuditNull() {
    AccountingAccountSchoolDto dto = new AccountingAccountSchoolDto();
    dto.setIdentifier("1");
    AccountingAccountSchool instance = accountingAccountSchoolMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void accountingAccountSchool_mapFromDto_whenAuditNotNull() {
    AccountingAccountSchoolDto dto = new AccountingAccountSchoolDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AccountingAccountSchool instance = accountingAccountSchoolMapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  /* AccountingOperation */
  
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
    createBusiness.computeBeneficiary(accountingOperation, request.getBeneficiary());
    assertEquals("CSP Cocody", accountingOperation.beneficiary);
  }

  @Test
  void computeBeneficiary_whenNotBlank() {
    AccountingOperationCreateRequestDto request = new AccountingOperationCreateRequestDto();
    request.setBeneficiary("b");
    AccountingOperation accountingOperation = new AccountingOperation();
    accountingOperation.accountType = AccountingAccountType.INCOME;
    createBusiness.computeBeneficiary(accountingOperation, request.getBeneficiary());
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
  
  @Test
  void cancel() {
    ByIdentifierRequestDto request = new ByIdentifierRequestDto();
    request.setIdentifier("tocancel");
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingOperation.ENTITY_NAME);
    cancelBusiness.process(request);
    assertEquals(count, count(entityManager, AccountingOperation.ENTITY_NAME));
  }
  
  @Test
  void validator_validateCanceled_whenNull() {
    assertFalse(validator.validateCanceled(null, null, new StringList()));
  }
  
  @Test
  void validator_validateCanceled_whenFalse() {
    assertFalse(validator.validateCanceled(false, null, new StringList()));
  }
  
  @Test
  void validator_validateCanceled_whenTrue() {
    assertTrue(validator.validateCanceled(true, null, new StringList()));
  }
  
  @Test
  void validator_validateCanceled_whenPaymentNotNull() {
    assertTrue(validator.validateCanceled(null, new Payment(), new StringList()));
  }

  @Test
  void mapToDto_whenNull() {
    assertNull(mapper.mapToDto(null));
  }
  
  @Test
  void mapToDto_whenNotNull() {
    AccountingOperation instance = new AccountingOperation();
    instance.setIdentifier("1");
    instance.setAudit(new Audit());
    instance.getAudit().setWho("christian");
    AccountingOperationDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertEquals(instance.getAudit().getWho(), dto.getAudit().getWho());
  }
  
  @Test
  void mapToDto_whenNotNullAndAuditNull() {
    AccountingOperation instance = new AccountingOperation();
    instance.setIdentifier("1");
    AccountingOperationDto dto = mapper.mapToDto(instance);
    assertEquals(instance.getIdentifier(), dto.getIdentifier());
    assertNull(dto.getAudit());
  }
  
  @Test
  void mapFromDto_whenNull() {
    assertNull(mapper.mapFromDto(null));
  }
  
  @Test
  void mapFromDto_whenAuditNull() {
    AccountingOperationDto dto = new AccountingOperationDto();
    dto.setIdentifier("1");
    AccountingOperation instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(null, instance.getAudit());
  }
  
  @Test
  void mapFromDto_whenAuditNotNull() {
    AccountingOperationDto dto = new AccountingOperationDto();
    dto.setIdentifier("1");
    dto.setAudit(new AuditDto());
    dto.getAudit().setWho("meliane");
    AccountingOperation instance = mapper.mapFromDto(dto);
    assertEquals(dto.getIdentifier(), instance.getIdentifier());
    assertEquals(dto.getAudit().getWho(), instance.getAudit().getWho());
  }
  
  /* AccountingOperationAccount */
  
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
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/accountingoperationbusiness.sql");
    }
  }
}
