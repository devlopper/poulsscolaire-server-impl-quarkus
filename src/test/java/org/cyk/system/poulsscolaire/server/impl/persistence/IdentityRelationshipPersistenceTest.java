package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.registration.IdentityRelationshipDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
class IdentityRelationshipPersistenceTest {

  @Inject
  IdentityRelationshipPersistence persistence;

  @Test
  void getName() {
    assertEquals(IdentityRelationshipDto.NAME, persistence.getName());
  }
}
