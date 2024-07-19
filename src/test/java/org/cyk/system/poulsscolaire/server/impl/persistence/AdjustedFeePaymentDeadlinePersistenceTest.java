package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AdjustedFeePaymentDeadlinePersistenceTest {

  @Inject
  AdjustedFeePaymentDeadlinePersistence persistence;

  @Test
  void getName() {
    assertEquals(AdjustedFeePaymentDeadlineDto.NAME, persistence.getName());
  }

}
