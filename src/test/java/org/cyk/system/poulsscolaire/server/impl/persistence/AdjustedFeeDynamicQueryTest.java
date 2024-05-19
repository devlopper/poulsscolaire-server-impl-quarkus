package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AdjustedFeeDynamicQueryTest {

  @Inject
  AdjustedFeeDynamicQuery dynamicQuery;

  DynamicQueryParameters<AdjustedFee> parameters = new DynamicQueryParameters<>();

  @Test
  void buildQueryString() {
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_LEFT_TO_PAY_AS_STRING);
    assertEquals(
        "SELECT CASE WHEN t.amount.optional THEN 0 ELSE COALESCE(t.amount.value,0) END"
            + " - SUM(COALESCE(p.amount,0)) FROM AdjustedFee t "
            + "ORDER BY t.fee.category.name DESC,t.registration.code DESC",
        dynamicQuery.buildQueryString(parameters));
  }
}
