package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.AdjustedFeePaymentDeadlineDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AdjustedFeePaymentDeadlineDynamicQueryTest.Profile.class)
class AdjustedFeePaymentDeadlineDynamicQueryTest {

  @Inject
  AdjustedFeePaymentDeadlineDynamicQuery dynamicQuery;

  DynamicQueryParameters<AdjustedFeePaymentDeadline> parameters = new DynamicQueryParameters<>();

  @Test
  void getMany() {
    parameters.projection().addNames(AdjustedFeePaymentDeadlineDto.JSON_IDENTIFIER);
    assertEquals(true, dynamicQuery.getMany(parameters).size() > 0);
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script",
          "sql/adjustedfeepaymentdeadlinedynamicquery.sql");
    }
  }
}
