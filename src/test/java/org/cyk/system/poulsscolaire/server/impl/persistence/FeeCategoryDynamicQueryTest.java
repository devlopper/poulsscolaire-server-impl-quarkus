package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.FeeCategoryDto;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(FeeCategoryDynamicQueryTest.Profile.class)
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

  @ParameterizedTest
  @CsvSource({"1,90 000", "2,30 000"})
  void getToPay(String identifier, String expected) {
    parameters.projection().addNames(FeeCategoryDto.JSON_TOTAL_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(expected, feeCategory.totalAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,20 000", "2,12 000"})
  void getRegistrationToPay(String identifier, String expected) {
    parameters.projection().addNames(FeeCategoryDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(expected, feeCategory.totalRegistrationAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,5", "2,30 000"})
  void getPaid(String identifier, String expected) {
    parameters.projection().addNames(FeeCategoryDto.JSON_PAID_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(expected, feeCategory.paidAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,5", "2,12 000"})
  void getRegistrationPaid(String identifier, String expected) {
    parameters.projection().addNames(FeeCategoryDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(expected, feeCategory.paidRegistrationAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,89 995", "2,0"})
  void getPayable(String identifier, String expected) {
    parameters.projection().addNames(FeeCategoryDto.JSON_PAYABLE_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(expected, feeCategory.payableAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"1,19 995", "2,0"})
  void getRegistrationPayable(String identifier, String expected) {
    parameters.projection().addNames(FeeCategoryDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(expected, feeCategory.payableRegistrationAmountAsString);
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/feecategorydynamicuqery.sql");
    }
  }
}
