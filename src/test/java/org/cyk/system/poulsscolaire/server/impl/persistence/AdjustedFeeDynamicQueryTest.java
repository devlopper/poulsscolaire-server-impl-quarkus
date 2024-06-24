package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeFilter;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AdjustedFeeDynamicQueryTest.Profile.class)
class AdjustedFeeDynamicQueryTest {

  @Inject
  AdjustedFeeDynamicQuery dynamicQuery;

  DynamicQueryParameters<AdjustedFee> parameters = new DynamicQueryParameters<>();

  @Test
  void formatCaseWhenOptionalFormat() {
    assertEquals("CASE WHEN t.amount.optional THEN IS_OPTIONAL ELSE IS_NOT_OPTIONAL END",
        dynamicQuery.formatCaseOptional("IS_OPTIONAL", "IS_NOT_OPTIONAL"));
  }

  @Test
  void amountPaidSubquery() {
    assertEquals(
        "SELECT SUM(COALESCE(p.amount,0)) FROM PaymentAdjustedFee p WHERE p.adjustedFee = t",
        dynamicQuery.amountPaidSubquery());
  }

  @Test
  void getMany() {
    parameters.projection().addNames(AdjustedFeeDto.JSON_IDENTIFIER);
    assertEquals(true, dynamicQuery.getMany(parameters).size() > 0);
  }

  @Test
  void get_whenProjectionDeadlineOver() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_DEADLINE_OVER,
        AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID_AS_STRING);
    parameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER, "deadlineover");
    AdjustedFee adjustedFee = dynamicQuery.getOne(parameters);
    assertEquals(true, adjustedFee.amountDeadlineOver);
  }

  @Test
  void get_whenProjectionAmountValuePayable() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAYABLE,
        AdjustedFeeDto.JSON_AMOUNT_VALUE_PAYABLE_AS_STRING);
    parameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER, "amountvaluepayable");
    AdjustedFee adjustedFee = dynamicQuery.getOne(parameters);
    assertEquals(999999, adjustedFee.amountValuePayable);
    assertEquals("999 999", adjustedFee.amountValuePayableAsString);
  }

  @Test
  void get_whenFilterAmountValuePayableEqualsZero() {
    parameters.projection().addNames(AdjustedFeeDto.JSON_IDENTIFIER);
    parameters.filter().addCriteria(AdjustedFeeFilter.JSON_AMOUNT_VALUE_PAYABLE_EQUALS_ZERO, true);
    List<AdjustedFee> instances = dynamicQuery.getMany(parameters);
    assertLinesMatch(List.of("deadlineover", "payableequalszero"),
        instances.stream().map(i -> i.getIdentifier()).toList());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/adjustedfeedynamicquery.sql");
    }
  }
}
