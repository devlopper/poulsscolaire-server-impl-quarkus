package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import ci.gouv.dgbf.extension.server.service.api.request.ProjectionDto;
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
  @CsvFileSource(resources = {"feecategorydynamicquery_getsumpaymentquery.csv"},
      useHeadersInDisplayName = true)
  void getSumPaymentQuery(String name, String expected) {
    assertEquals(expected, dynamicQuery.getSumPaymentQuery(new ProjectionDto().addNames(name)));
  }

  @ParameterizedTest
  @CsvSource({"1,20 000,5,19 995", "2,12 000,12 000,0"})
  void get(String identifier, String total, String paid, String payable) {
    parameters.projection().addNames(FeeCategoryDto.JSON_TOTAL_REGISTRATION_AMOUNT_AS_STRING,
        FeeCategoryDto.JSON_PAID_REGISTRATION_AMOUNT_AS_STRING,
        FeeCategoryDto.JSON_PAYABLE_REGISTRATION_AMOUNT_AS_STRING);
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(FeeCategoryDto.JSON_IDENTIFIER, identifier);
    FeeCategory feeCategory = dynamicQuery.getOne(parameters);
    assertEquals(total, feeCategory.getTotalRegistrationAmountAsString());
    assertEquals(paid, feeCategory.getPaidRegistrationAmountAsString());
    assertEquals(payable, feeCategory.getPayableRegistrationAmountAsString());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/feecategorydynamicuqery.sql");
    }
  }
}
