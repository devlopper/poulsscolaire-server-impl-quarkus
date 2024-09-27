package org.cyk.system.poulsscolaire.server.impl.business.accountingaccountschool;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.test.AbstractTest;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolService.AccountingAccountSchoolCreateRequestDto;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountSchoolService.AccountingAccountSchoolUpdateRequestDto;
import org.cyk.system.poulsscolaire.server.impl.persistence.AccountingAccountSchool;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AccountingAccountSchoolBusinessTest.Profile.class)
class AccountingAccountSchoolBusinessTest extends AbstractTest {

  @Inject
  EntityManager entityManager;

  @Inject
  AccountingAccountSchoolCreateBusiness createBusiness;

  @Inject
  AccountingAccountSchoolReadManyBusiness readManyBusiness;

  @Inject
  AccountingAccountSchoolReadOneBusiness readOneBusiness;

  @Inject
  AccountingAccountSchoolReadByIdentifierBusiness readByIdentifierBusiness;

  @Inject
  AccountingAccountSchoolUpdateBusiness updateBusiness;

  @Inject
  AccountingAccountSchoolDeleteBusiness deleteBusiness;

  @Test
  void create() {
    AccountingAccountSchoolCreateRequestDto request = new AccountingAccountSchoolCreateRequestDto();
    request.setAccountIdentifier("1");
    request.setSchoolIdentifier("1");
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingAccountSchool.ENTITY_NAME);
    createBusiness.process(request);
    assertEquals(count + 1, count(entityManager, AccountingAccountSchool.ENTITY_NAME));
  }

  @Test
  void update() {
    AccountingAccountSchoolUpdateRequestDto request = new AccountingAccountSchoolUpdateRequestDto();
    request.setIdentifier("toupdate");
    request.setAccountIdentifier("1");
    request.setSchoolIdentifier("2");
    request.setAuditWho("christian");
    long count = count(entityManager, AccountingAccountSchool.ENTITY_NAME);
    updateBusiness.process(request);
    assertEquals(count, count(entityManager, AccountingAccountSchool.ENTITY_NAME));
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/accountingaccountschoolbusiness.sql");
    }
  }
}
