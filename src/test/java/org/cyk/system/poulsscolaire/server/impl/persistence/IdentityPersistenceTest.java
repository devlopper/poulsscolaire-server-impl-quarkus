package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class IdentityPersistenceTest {

  @Inject
  IdentityPersistence persistence;
  
  @Test
  void getName() {
    assertEquals("identité", persistence.getName());
  }
}
