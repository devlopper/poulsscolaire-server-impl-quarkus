package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.accounting.AccountingOperationDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AccountingOperationPersistenceTest {

  @Inject
  AccountingOperationPersistence persistence;

  @Test
  void getName() {
    assertEquals(AccountingOperationDto.NAME, persistence.getName());
  }

  @Test
  void entities() {
    assertNotNull(new AccountingOperationAmount());
  }
}
