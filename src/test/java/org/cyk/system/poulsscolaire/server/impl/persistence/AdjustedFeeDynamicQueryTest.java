package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeeDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AdjustedFeeDynamicQueryTest.Profile.class)
class AdjustedFeeDynamicQueryTest {

  @Inject
  AdjustedFeeDynamicQuery dynamicQuery;

  DynamicQueryParameters<AdjustedFee> parameters = new DynamicQueryParameters<>();

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

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/adjustedfeedynamicquery.sql");
    }
  }
}
