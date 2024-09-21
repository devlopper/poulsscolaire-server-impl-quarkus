package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AccountingPlanPersistenceTest.Profile.class)
class AccountingPlanPersistenceTest {

  @Inject
  AccountingPlanPersistence persistence;

  @Test
  void getName() {
    assertEquals(AccountingPlanDto.NAME, persistence.getName());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/accountingplanpersistence.sql");
    }
  }
}
