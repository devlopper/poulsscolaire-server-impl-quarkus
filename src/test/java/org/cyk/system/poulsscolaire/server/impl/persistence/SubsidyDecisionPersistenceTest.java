package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class SubsidyDecisionPersistenceTest {

  @Test
  void instantiate() {
    assertNotNull(new SubsidyDecisionRegistrations());
    assertNotNull(new SubsidyDecisionPayments());
  }
}
