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
    assertEquals("SELECT SUM(CASE WHEN a.optional THEN 0 ELSE COALESCE(a.value,0) END) "
        + "FROM School t " + "LEFT JOIN Schooling s ON s.schoolIdentifier = t.identifier "
        + "LEFT JOIN Registration r ON r.schooling = s "
        + "LEFT JOIN AdjustedFee af ON af.registration = r "
        + "LEFT JOIN Amount a ON a = af.amount " + "GROUP BY t.identifier,t.name "
        + "ORDER BY t.name ASC", dynamicQuery.buildQueryString(parameters));
  }

  @Test
  void buildQueryString_whenPaidAmount() {
    parameters.projection().addNames(SchoolDto.JSON_PAID_AMOUNT_AS_STRING);
    assertEquals(
        "SELECT SUM(COALESCE(paf.amount,0)) FROM School t "
            + "LEFT JOIN Schooling s ON s.schoolIdentifier = t.identifier "
            + "LEFT JOIN Registration r ON r.schooling = s "
            + "LEFT JOIN Payment p ON p.registration = r "
            + "LEFT JOIN PaymentAdjustedFee paf ON paf.payment = p "
            + "GROUP BY t.identifier,t.name ORDER BY t.name ASC",
        dynamicQuery.buildQueryString(parameters));
  }

  @Test
  void getSumPaymentQuery_whenHasTotalProjection() {
    parameters.projection().addNames(SchoolDto.JSON_TOTAL_AMOUNT_AS_STRING);
    assertEquals(
        "COALESCE((SELECT SUM(sqt.amount) FROM PaymentAdjustedFee sqt "
            + "WHERE sqt.payment.registration.schooling.schoolIdentifier = t.identifier),0)",
        dynamicQuery.getSumPaymentQuery(parameters.projection()));
  }

  @Test
  void getSumPaymentQuery_whenHasNoTotalProjection() {
    assertEquals(
        "SUM(COALESCE(paf.amount,0))",
        dynamicQuery.getSumPaymentQuery(parameters.projection()));
  }
}
