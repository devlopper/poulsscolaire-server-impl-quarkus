package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.cyk.system.poulsscolaire.server.api.fee.AmountService;
import org.junit.jupiter.api.Test;

class AmountTest {

  Amount amount = new Amount();

  @Test
  void set_whenArrayNull() {
    AmountService.AmountCreateRequestDto request = new AmountService.AmountCreateRequestDto();
    amount.set(request, null);
    assertNull(amount.deadline);
  }

  @Test
  void set_whenArrayNotNull() {
    AmountService.AmountCreateRequestDto request = new AmountService.AmountCreateRequestDto();
    amount.set(request, new Object[] {new Deadline()});
    assertNotNull(amount.deadline);
  }
}
