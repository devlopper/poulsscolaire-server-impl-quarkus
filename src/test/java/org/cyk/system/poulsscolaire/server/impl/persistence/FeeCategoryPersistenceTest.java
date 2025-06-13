package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class FeeCategoryPersistenceTest {

  @Inject
  FeeCategoryPersistence persistence;
    
  @Test
  void instantiate() {
    assertNotNull(new StockDistributionQuantity());
  }
}
