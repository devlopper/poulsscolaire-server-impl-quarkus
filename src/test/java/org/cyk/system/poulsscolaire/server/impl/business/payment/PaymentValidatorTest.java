package org.cyk.system.poulsscolaire.server.impl.business.payment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.core.StringList;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
class PaymentValidatorTest {

  @Inject
  PaymentValidator validator;
  StringList messages = new StringList();
  
  @Test
  void validateFirstPayment_whenAmountIsLess() {
    assertTrue(validator.validateFirstPayment(1, 0, new StringList()));
  }
  
  @Test
  void validateFirstPayment_whenAmountIsGreaterThanOrEqual() {
    assertFalse(validator.validateFirstPayment(1, 1, new StringList()));
  }
}
