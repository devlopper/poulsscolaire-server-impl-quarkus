package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
  void buildQueryString() {
    parameters.projection().addNames(PaymentDto.JSON_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT COALESCE(SUM(paf.amount), 0) FROM Payment t "
            + "LEFT JOIN PaymentAdjustedFee paf ON paf.payment = t "
            + "GROUP BY t.identifier,t.code ORDER BY t.code ASC",
        dynamicQuery.buildQueryString(parameters));
  }
}
