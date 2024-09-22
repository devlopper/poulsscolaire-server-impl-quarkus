package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingAccountDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AccountingAccountPersistenceTest.Profile.class)
class AccountingAccountPersistenceTest {

  @Inject
  AccountingAccountPersistence persistence;

  @Test
  void getName() {
    assertEquals(AccountingAccountDto.NAME, persistence.getName());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/accountingaccountpersistence.sql");
    }
  }
}
