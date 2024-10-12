package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingPlanDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AccountingPlanPersistenceTest {

  @Inject
  AccountingPlanPersistence persistence;

  @Test
  void getName() {
    assertEquals(AccountingPlanDto.NAME, persistence.getName());
  }
}
