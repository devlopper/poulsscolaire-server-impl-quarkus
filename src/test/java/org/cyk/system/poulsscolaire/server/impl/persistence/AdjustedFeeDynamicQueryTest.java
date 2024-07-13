package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
  void instanciateAdjustedFeeAmountToPay() {
    assertNotNull(new AdjustedFeeAmountToPay());
  }
  
  @Test
  void instanciateAdjustedFeeAmountPaid() {
    assertNotNull(new AdjustedFeeAmountPaid());
  }
  
  @Test
  void formatCaseWhenOptionalFormat() {
    assertEquals("CASE WHEN t.amount.optional THEN IS_OPTIONAL ELSE IS_NOT_OPTIONAL END",
        dynamicQuery.formatCaseOptional("IS_OPTIONAL", "IS_NOT_OPTIONAL"));
  }

  @Test
  void amountPaidSubquery() {
    assertEquals(
        "(SELECT COALESCE(SUM(p.amount),0) FROM PaymentAdjustedFee p WHERE p.adjustedFee = t)",
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
  void buildQueryString_whenProjectionAmountValueToPay() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_TO_PAY_AS_STRING);
    parameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER, "amountvaluepayable");
    assertEquals("SELECT afatp.amount FROM AdjustedFee t "
        + "LEFT JOIN AdjustedFeeAmountToPay afatp ON afatp.identifier = t.identifier "
        + "WHERE t.identifier = :identifiant", dynamicQuery.buildQueryString(parameters));
  }
  
  @Test
  void buildQueryString_whenProjectionAmountValuePaid() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID_AS_STRING);
    parameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER, "amountvaluepayable");
    assertEquals("SELECT afap.amount FROM AdjustedFee t "
        + "LEFT JOIN AdjustedFeeAmountPaid afap ON afap.identifier = t.identifier "
        + "WHERE t.identifier = :identifiant", dynamicQuery.buildQueryString(parameters));
  }
  
  @Test
  void get_whenProjectionAmountValueToPay() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_TO_PAY_AS_STRING);
    parameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER, "amountvaluepayable");
    AdjustedFee adjustedFee = dynamicQuery.getOne(parameters);
    assertEquals("1 000 000", adjustedFee.amountValueToPayAsString);
  }
  
  @Test
  void get_whenProjectionAmountValuePaid() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(AdjustedFeeDto.JSON_AMOUNT_VALUE_PAID_AS_STRING);
    parameters.filter().addCriteria(AdjustedFeeDto.JSON_IDENTIFIER, "amountvaluepayable");
    AdjustedFee adjustedFee = dynamicQuery.getOne(parameters);
    assertEquals("1", adjustedFee.amountValuePaidAsString);
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
  void get_whenFilterAmountValuePayableEqualsZeroTrue() {
    parameters.projection().addNames(AdjustedFeeDto.JSON_IDENTIFIER);
    parameters.filter().addCriteria(AdjustedFeeFilter.JSON_AMOUNT_VALUE_PAYABLE_EQUALS_ZERO, true);
    List<AdjustedFee> instances = dynamicQuery.getMany(parameters);
    assertLinesMatch(List.of("deadlineover", "payableequalszero"),
        instances.stream().map(i -> i.getIdentifier()).toList());
  }
  
  @Test
  void get_whenFilterAmountValuePayableEqualsZeroFalse() {
    parameters.projection().addNames(AdjustedFeeDto.JSON_IDENTIFIER);
    parameters.filter().addCriteria(AdjustedFeeFilter.JSON_AMOUNT_VALUE_PAYABLE_EQUALS_ZERO, false);
    List<AdjustedFee> instances = dynamicQuery.getMany(parameters);
    assertLinesMatch(List.of("amountvaluepayable"),
        instances.stream().map(i -> i.getIdentifier()).toList());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/adjustedfeedynamicquery.sql");
    }
  }
}
