package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AccountingOperationDynamicQueryTest {

  @Inject
  AccountingOperationDynamicQuery dynamicQuery;

  DynamicQueryParameters<AccountingOperation> parameters = new DynamicQueryParameters<>();
  
  @Test
  void getMany() {
    assertEquals(0, dynamicQuery.getMany(parameters).size());
  }
  
  @Test
  void entities() {
    assertNotNull(new AccountingOperationAmount());
  }
}
