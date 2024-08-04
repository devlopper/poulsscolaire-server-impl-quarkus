package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
class SchoolDynamicQueryTest {

  @Inject
  SchoolDynamicQuery dynamicQuery;

  DynamicQueryParameters<School> parameters = new DynamicQueryParameters<>();

  @Test
  void buildQueryString_whenTotalAmount() {
    parameters.projection().addNames(SchoolDto.JSON_TOTAL_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afa.amountToPay) FROM School t "
            + "LEFT JOIN AdjustedFeeAmounts afa ON afa.schoolIdentifier = t.identifier "
            + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        dynamicQuery.buildQueryString(parameters));
  }

  @Test
  void buildQueryString_whenPaidAmount() {
    parameters.projection().addNames(SchoolDto.JSON_PAID_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afa.amountPaid) FROM School t "
        + "LEFT JOIN AdjustedFeeAmounts afa ON afa.schoolIdentifier = t.identifier "
        + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        dynamicQuery.buildQueryString(parameters));
  }

  @Test
  void buildQueryString_whenPayableAmount() {
    parameters.projection().addNames(SchoolDto.JSON_PAYABLE_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afa.amountLeftToPay) "
        + "FROM School t "
        + "LEFT JOIN AdjustedFeeAmounts afa ON afa.schoolIdentifier = t.identifier "
        + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        dynamicQuery.buildQueryString(parameters));
  }
  
  @Test
  void instantiate() {
    assertNotNull(new SchoolBranch());
    assertNotNull(new SchoolPeriod());
  }
}
