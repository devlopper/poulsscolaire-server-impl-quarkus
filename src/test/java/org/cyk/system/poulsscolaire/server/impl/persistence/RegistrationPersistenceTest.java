package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.core.ArrayContainer;
import ci.gouv.dgbf.extension.core.ArrayHelper;
import ci.gouv.dgbf.extension.core.NumberHelper;
import ci.gouv.dgbf.extension.core.TimeHelper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class RegistrationPersistenceTest {

  @Inject
  RegistrationPersistence persistence;

  @Inject
  AdjustedFeeDynamicQuery adjustedFeeDynamicQuery;

  @Test
  void getName() {
    assertEquals("inscription", persistence.getName());
  }

  @Test
  void adjustedFeeDynamicQuery_registrationAsStringConsumer() {
    AdjustedFee adjustedFee = new AdjustedFee();
    adjustedFeeDynamicQuery.registrationAsStringConsumer().accept(adjustedFee,
        new ArrayContainer(new Object[] {"a", "b", "c", "d", "e", "f"}, new ArrayHelper(),
            new NumberHelper(), new TimeHelper()));
    assertEquals("a | (b) | d - e f", adjustedFee.registrationAsString);
  }
}
