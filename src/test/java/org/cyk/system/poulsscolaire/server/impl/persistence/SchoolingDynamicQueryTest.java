package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingDto;
import org.cyk.system.poulsscolaire.server.api.configuration.SchoolingFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(SchoolingDynamicQueryTest.Profile.class)
class SchoolingDynamicQueryTest {

  @Inject
  SchoolingDynamicQuery dynamicQuery;

  DynamicQueryParameters<Schooling> parameters = new DynamicQueryParameters<>();

  @Inject
  EntityManager entityManager;

  @Test
  void getOne_schoolAsString() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(SchoolingDto.JSON_SCHOOL_AS_STRING,
        SchoolingDto.JSON_SCHOOL_IDENTIFIER);
    parameters.filter().addCriteria(SchoolingDto.JSON_IDENTIFIER, "1");
    Schooling schooling = dynamicQuery.getOne(parameters);
    assertEquals("CSP Cocody", schooling.schoolAsString);
  }

  @Test
  void getOne_branchAsString() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(SchoolingDto.JSON_BRANCH_AS_STRING,
        SchoolingDto.JSON_BRANCH_IDENTIFIER);
    parameters.filter().addCriteria(SchoolingDto.JSON_IDENTIFIER, "1");
    Schooling schooling = dynamicQuery.getOne(parameters);
    assertEquals("6ieme", schooling.branchAsString);
  }

  @Test
  void getOne_periodAsString() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(SchoolingDto.JSON_PERIOD_AS_STRING,
        SchoolingDto.JSON_PERIOD_IDENTIFIER);
    parameters.filter().addCriteria(SchoolingDto.JSON_IDENTIFIER, "1");
    Schooling schooling = dynamicQuery.getOne(parameters);
    assertEquals("2023-2024", schooling.periodAsString);
  }

  @ParameterizedTest
  @CsvSource({"feesvalue1,140 000", "feesvalue2,243 000", "nofeesvalue,0"})
  void getOne_feesValueAsString(String identifier, String amount) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(SchoolingDto.JSON_IDENTIFIER,
        SchoolingDto.JSON_FEE_AMOUNT_VALUE_AS_STRING);
    parameters.filter().addCriteria(SchoolingDto.JSON_IDENTIFIER, identifier);
    Schooling schooling = dynamicQuery.getOne(parameters);
    assertEquals(amount, schooling.feeAmountValueAsString);
  }

  @ParameterizedTest
  @CsvSource({"feesvalue1,140 000,", "feesvalue1,130 000,false", "feesvalue1,10 000,true",
      "feesvalue2,243 000,"})
  void getOne_feesValueAsString_whenFilterByOptional(String identifier, String amount,
      Boolean optional) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(SchoolingDto.JSON_IDENTIFIER,
        SchoolingDto.JSON_FEE_AMOUNT_VALUE_AS_STRING);
    parameters.filter().addCriteria(SchoolingDto.JSON_IDENTIFIER, identifier);
    parameters.filter().addCriteria(SchoolingFilter.JSON_IDENTIFIER, identifier)
        .addCriteria(SchoolingFilter.JSON_FEE_AMOUNT_OPTIONAL, optional);
    Schooling schooling = dynamicQuery.getOne(parameters);
    assertEquals(amount, schooling.feeAmountValueAsString);
  }

  @ParameterizedTest
  @CsvSource({"feesvalue1,25 000", "feesvalue2,80 000"})
  void getOne_feesRegistrationValuePartAsString(String identifier, String amount) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(SchoolingDto.JSON_IDENTIFIER,
        SchoolingDto.JSON_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING);
    parameters.filter().addCriteria(SchoolingDto.JSON_IDENTIFIER, identifier);
    Schooling schooling = dynamicQuery.getOne(parameters);
    assertEquals(amount, schooling.feeAmountRegistrationValuePartAsString);
  }

  @ParameterizedTest
  @CsvSource({"feesvalue1,25 000,", "feesvalue2,80 000,"})
  void getOne_feesRegistrationValuePartAsString_whenFilterByOptional(String identifier,
      String amount, Boolean optional) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(SchoolingDto.JSON_IDENTIFIER,
        SchoolingDto.JSON_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING);
    parameters.filter().addCriteria(SchoolingFilter.JSON_IDENTIFIER, identifier)
        .addCriteria(SchoolingFilter.JSON_FEE_AMOUNT_OPTIONAL, optional);
    Schooling schooling = dynamicQuery.getOne(parameters);
    assertEquals(amount, schooling.feeAmountRegistrationValuePartAsString);
  }

  @Test
  void getMany_feesValueAsString() {
    parameters.projection().addNames(SchoolingDto.JSON_IDENTIFIER,
        SchoolingDto.JSON_FEE_AMOUNT_VALUE_AS_STRING);
    List<Schooling> schoolings = dynamicQuery.getMany(parameters);
    assertEquals(5, schoolings.size());
    Collection<String> amounts = schoolings.stream().map(s -> s.feeAmountValueAsString).toList();
    assertTrue(amounts.contains("140 000"));
    assertTrue(amounts.contains("243 000"));
    assertTrue(amounts.contains("0"));
  }

  @Test
  void getMany_feesRegistrationValuePartAsString() {
    parameters.projection().addNames(SchoolingDto.JSON_IDENTIFIER,
        SchoolingDto.JSON_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING);
    List<Schooling> schoolings = dynamicQuery.getMany(parameters);
    assertLinesMatch(List.of("0", "0", "25 000", "80 000", "0"),
        schoolings.stream().map(s -> s.feeAmountRegistrationValuePartAsString).toList());
  }

  @ParameterizedTest
  @CsvSource({"feesvalue1,130 000", "feesvalue2,243 000"})
  void getOne_notOptionalFeeValueAsString(String identifier, String amount) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(SchoolingDto.JSON_IDENTIFIER,
        SchoolingDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_VALUE_AS_STRING);
    parameters.filter().addCriteria(SchoolingDto.JSON_IDENTIFIER, identifier);
    Schooling schooling = dynamicQuery.getOne(parameters);
    assertEquals(amount, schooling.notOptionalFeeAmountValueAsString);
  }

  @ParameterizedTest
  @CsvSource({"feesvalue1,25 000", "feesvalue2,80 000"})
  void getOne_notOptionalFeeAmountRegistrationValuePartAsString(String identifier, String amount) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(SchoolingDto.JSON_IDENTIFIER,
        SchoolingDto.JSON_NOT_OPTIONAL_FEE_AMOUNT_REGISTRATION_VALUE_PART_AS_STRING);
    parameters.filter().addCriteria(SchoolingDto.JSON_IDENTIFIER, identifier);
    Schooling schooling = dynamicQuery.getOne(parameters);
    assertEquals(amount, schooling.notOptionalFeeAmountRegistrationValuePartAsString);
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/schoolingdynamicquery.sql");
    }
  }
}
