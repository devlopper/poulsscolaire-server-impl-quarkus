package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.configuration.BranchInstanceDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
class BranchInstancePersistenceTest {

  @Inject
  BranchInstancePersistence persistence;
  
  @Test
  void getName() {
    assertEquals(BranchInstanceDto.NAME, persistence.getName());
  }
}
