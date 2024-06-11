package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.service.api.request.ProjectionDto;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@QuarkusTest
class FeeCategoryDynamicQueryTest {

  @Inject
  FeeCategoryDynamicQuery dynamicQuery;

  DynamicQueryParameters<FeeCategory> parameters = new DynamicQueryParameters<>();

  @ParameterizedTest
  @CsvFileSource(resources = {"feecategorydynamicquery_buildquery_projection.csv"},
      useHeadersInDisplayName = true)
  void buildQuery_amount(String amount, String expected) {
    parameters.projection().addNames(amount);
    assertEquals(expected, dynamicQuery.buildQueryString(parameters));
  }

  @Test
  void getSumPaymentQuery_whenHasNotTotalProjection() {
    assertEquals("SUM(COALESCE(paf.amount,0))",
        dynamicQuery.getSumPaymentQuery(new ProjectionDto().addNames("a")));
  }

  @Test
  void getSumPaymentQuery_whenHasTotalProjection() {
    assertEquals(
        "COALESCE((SELECT SUM(v.amount) FROM PaymentAdjustedFee v "
        + "WHERE v.adjustedFee.fee.category = t),0)",
        dynamicQuery.getSumPaymentQuery(
            new ProjectionDto().addNames(FeeCategoryDto.JSON_TOTAL_AMOUNT_AS_STRING)));
  }
}
