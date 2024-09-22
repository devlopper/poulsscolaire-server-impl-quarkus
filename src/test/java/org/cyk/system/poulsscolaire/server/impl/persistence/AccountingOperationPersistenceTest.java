package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AccountingOperationPersistenceTest.Profile.class)
class AccountingOperationPersistenceTest {

  @Inject
  AccountingOperationPersistence persistence;

  @Test
  void getName() {
    assertEquals(AccountingOperationDto.NAME, persistence.getName());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/accountingoperationpersistence.sql");
    }
  }
}
