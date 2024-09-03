package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.registration.RegistrationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(RegistrationDynamicQueryTest.Profile.class)
class RegistrationDynamicQueryTest {

  @Inject
  RegistrationDynamicQuery dynamicQuery;

  DynamicQueryParameters<Registration> parameters = new DynamicQueryParameters<>();

  @ParameterizedTest
  @CsvFileSource(resources = {"registrationdynamicquery_buildquery_projection.csv"},
      useHeadersInDisplayName = true)
  void buildQuery_amount(String amount, String expected) {
    parameters.projection().addNames(amount);
    assertEquals(expected, dynamicQuery.buildQueryString(parameters));
  }

  @ParameterizedTest
  @CsvSource({"i1,120 000"})
  void getOne_totalAmountAsString(String identifier, String value) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(RegistrationDto.JSON_IDENTIFIER,
        RegistrationDto.JSON_TOTAL_AMOUNT_AS_STRING);
    parameters.filter().addCriteria(RegistrationDto.JSON_IDENTIFIER, identifier);
    Registration registration = dynamicQuery.getOne(parameters);
    assertEquals(value, registration.totalAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"i1,5"})
  void getOne_paidAmountAsString(String identifier, String value) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(RegistrationDto.JSON_IDENTIFIER,
        RegistrationDto.JSON_PAID_AMOUNT_AS_STRING);
    parameters.filter().addCriteria(RegistrationDto.JSON_IDENTIFIER, identifier);
    Registration registration = dynamicQuery.getOne(parameters);
    assertEquals(value, registration.paidAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"i1,119 995"})
  void getOne_payableAmountAsString(String identifier, String value) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(RegistrationDto.JSON_IDENTIFIER,
        RegistrationDto.JSON_PAYABLE_AMOUNT_AS_STRING);
    parameters.filter().addCriteria(RegistrationDto.JSON_IDENTIFIER, identifier);
    Registration registration = dynamicQuery.getOne(parameters);
    assertEquals(value, registration.payableAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"i1,119 995"})
  void getMany_payableAmountAsString(String identifier, String value) {
    parameters.projection().addNames(RegistrationDto.JSON_IDENTIFIER,
        RegistrationDto.JSON_PAYABLE_AMOUNT_AS_STRING);
    parameters.filter().addCriteria(RegistrationDto.JSON_IDENTIFIER, identifier);
    Registration registration = dynamicQuery.getMany(parameters).iterator().next();
    assertEquals(value, registration.payableAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"i1,120 000,5,119 995,20 000,5,19 995"})
  void getOne_amounts(String identifier, String total, String paid, String payable,
      String totalRegistration, String paidRegistration, String payableRegistration) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(RegistrationDto.JSON_IDENTIFIER,
        RegistrationDto.JSON_TOTAL_AMOUNT_AS_STRING, RegistrationDto.JSON_PAID_AMOUNT_AS_STRING,
        RegistrationDto.JSON_PAYABLE_AMOUNT_AS_STRING,
        RegistrationDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING,
        RegistrationDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING,
        RegistrationDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING);
    parameters.filter().addCriteria(RegistrationDto.JSON_IDENTIFIER, identifier);
    Registration registration = dynamicQuery.getOne(parameters);
    assertEquals(total, registration.totalAmountAsString);
    assertEquals(paid, registration.paidAmountAsString);
    assertEquals(payable, registration.payableAmountAsString);

    assertEquals(totalRegistration, registration.totalRegistrationAmountAsString);
    assertEquals(paidRegistration, registration.paidRegistrationAmountAsString);
    assertEquals(payableRegistration, registration.payableRegistrationAmountAsString);
  }

  @ParameterizedTest
  @CsvSource({"i1,20 000"})
  void getOne_totalRegistrationAmountAsString(String identifier, String amount) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(RegistrationDto.JSON_IDENTIFIER,
        RegistrationDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING);
    parameters.filter().addCriteria(RegistrationDto.JSON_IDENTIFIER, identifier);
    Registration registration = dynamicQuery.getOne(parameters);
    assertEquals(amount, registration.totalRegistrationAmountAsString);
  }
  
  @Test
  void getOne_studentAsString() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(RegistrationDto.JSON_STUDENT_AS_STRING);
    parameters.filter().addCriteria(RegistrationDto.JSON_IDENTIFIER, "i1");
    Registration registration = dynamicQuery.getOne(parameters);
    assertEquals("m01 - 1 1", registration.studentAsString);
  }
  
  @Test
  void getOne_branchInstanceAsString() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(RegistrationDto.JSON_BRANCH_INSTANCE_AS_STRING);
    parameters.filter().addCriteria(RegistrationDto.JSON_IDENTIFIER, "i1");
    Registration registration = dynamicQuery.getOne(parameters);
    assertEquals("CP1 A", registration.branchInstanceAsString);
  }
  
  @Test
  void getOne_asString() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(RegistrationDto.JSON_AS_STRING);
    parameters.filter().addCriteria(RegistrationDto.JSON_IDENTIFIER, "i1");
    Registration registration = dynamicQuery.getOne(parameters);
    assertEquals("1 | (CP1 A) | m01 - 1 1", registration.asString);
  }

  @Test
  void instantiate() {
    assertNotNull(new RegistrationView());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/registrationdynamicquery.sql");
    }
  }
}
