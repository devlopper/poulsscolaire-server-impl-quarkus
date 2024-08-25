package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.payment.PaymentDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
class PaymentDynamicQueryTest {

  @Inject
  PaymentDynamicQuery dynamicQuery;

  DynamicQueryParameters<Payment> parameters = new DynamicQueryParameters<>();

  @Test
  void instanciate() {
    assertNotNull(new PaymentAmounts());
    assertNotNull(new PaymentDates());
  }
  
  @Test
  void buildQueryString() {
    parameters.projection().addNames(PaymentDto.JSON_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT pa.total FROM Payment t "
            + "LEFT JOIN PaymentAmounts pa ON pa.identifier = t.identifier "
            + "ORDER BY t.code ASC",
        dynamicQuery.buildQueryString(parameters));
  }
}
