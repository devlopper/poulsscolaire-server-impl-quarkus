package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AmountDeadlinePersistenceTest {

  @Inject
  AmountDeadlinePersistence persistence;

  @Test
  void getName() {
    assertEquals(AmountDeadlineDto.NAME, persistence.getName());
  }

}
