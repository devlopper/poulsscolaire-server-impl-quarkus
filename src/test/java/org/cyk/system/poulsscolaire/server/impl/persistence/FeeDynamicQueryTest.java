package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.FeeDto;
import org.cyk.system.poulsscolaire.server.api.fee.FeeFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@QuarkusTest
@TestProfile(FeeDynamicQueryTest.Profile.class)
class FeeDynamicQueryTest {

  @Inject
  FeeDynamicQuery dynamicQuery;

  DynamicQueryParameters<Fee> parameters = new DynamicQueryParameters<>();

  @Test
  void buildQuery_whenProjectionSchoolingSchoolAsString() {
    parameters.projection().addNames(FeeDto.JSON_SCHOOLING_SCHOOL_AS_STRING);
    assertEquals(
        "SELECT school.name FROM Fee t "
            + "LEFT JOIN School school ON school.identifier = t.schooling.schoolIdentifier "
            + "ORDER BY t.amount.paymentOrderNumber ASC,t.category.name ASC",
        dynamicQuery.buildQueryString(parameters));
  }

  @ParameterizedTest
  @CsvSource(value = {"1,1,1,100,0", "2,1,1,500,75"})
  void getOne_whenSumAmountAndRegistration_whenFilterSchoolingAssignlentTypeSeniority(
      String schoolingIdentifier, String assignmenttypeIdentifier, String seniorityIdentifier,
      String expectedAmountSum, String expectedRegistrationSum) {
    parameters.setResultMode(ResultMode.ONE);
    parameters.projection().addNames(FeeDto.JSON_AMOUNT_VALUE_SUM_AS_STRING,
        FeeDto.JSON_AMOUNT_REGISTRATION_SUM_AS_STRING);
    parameters.filter().addCriteria(FeeFilter.JSON_SCHOOLING_IDENTIFIER, schoolingIdentifier);
    parameters.filter().addCriteria(FeeFilter.JSON_ASSIGNMENT_TYPE_IDENTIFIER,
        assignmenttypeIdentifier);
    parameters.filter().addCriteria(FeeFilter.JSON_SENIORITY_IDENTIFIER, seniorityIdentifier);
    Fee fee = dynamicQuery.getOne(parameters);
    assertEquals(expectedAmountSum, fee.amountValueSumAsString);
    assertEquals(expectedRegistrationSum, fee.amountRegistrationSumAsString);
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/feedynamicquery.sql");
    }
  }
}
