package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.cyk.system.poulsscolaire.server.api.fee.AmountService;
import org.junit.jupiter.api.Test;

class AmountTest {

  Amount amount = new Amount();

  @Test
  void set_whenArrayNull() {
    AmountService.AmountCreateRequestDto request = new AmountService.AmountCreateRequestDto();
    request.setOptional(false);
    amount.set(request, null);
    assertFalse(amount.optional);
  }

  @Test
  void set_whenArrayNotNull() {
    AmountService.AmountCreateRequestDto request = new AmountService.AmountCreateRequestDto();
    request.setOptional(false);
    amount.set(request, new Object[] {});
    assertFalse(amount.optional);
  }
}
