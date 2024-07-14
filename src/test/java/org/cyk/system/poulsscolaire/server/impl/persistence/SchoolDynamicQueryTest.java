package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        "SELECT SUM(afatp.amount) FROM School t "
            + "LEFT JOIN Schooling s ON s.schoolIdentifier = t.identifier "
            + "LEFT JOIN Registration r ON r.schooling = s "
            + "LEFT JOIN AdjustedFeeAmountToPay afatp ON afatp.schoolIdentifier = t.identifier "
            + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        dynamicQuery.buildQueryString(parameters));
  }

  @Test
  void buildQueryString_whenPaidAmount() {
    parameters.projection().addNames(SchoolDto.JSON_PAID_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(afap.amount) FROM School t "
        + "LEFT JOIN Schooling s ON s.schoolIdentifier = t.identifier "
        + "LEFT JOIN Registration r ON r.schooling = s "
        + "LEFT JOIN AdjustedFeeAmountPaid afap ON afap.schoolIdentifier = t.identifier "
        + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        dynamicQuery.buildQueryString(parameters));
  }

  @Test
  void buildQueryString_whenPayableAmount() {
    parameters.projection().addNames(SchoolDto.JSON_PAYABLE_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(COALESCE(afatp.amount,0)) - SUM(COALESCE(afap.amount,0)) "
        + "FROM School t LEFT JOIN Schooling s ON s.schoolIdentifier = t.identifier "
        + "LEFT JOIN Registration r ON r.schooling = s "
        + "LEFT JOIN AdjustedFeeAmountToPay afatp ON afatp.schoolIdentifier = t.identifier "
        + "LEFT JOIN AdjustedFeeAmountPaid afap ON afap.schoolIdentifier = t.identifier G"
        + "ROUP BY t.identifier,t.name ORDER BY t.name ASC",
        dynamicQuery.buildQueryString(parameters));
  }
}
