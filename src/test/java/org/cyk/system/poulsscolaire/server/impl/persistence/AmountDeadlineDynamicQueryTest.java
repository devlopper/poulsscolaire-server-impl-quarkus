package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.AmountDeadlineDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(AmountDeadlineDynamicQueryTest.Profile.class)
class AmountDeadlineDynamicQueryTest {

  @Inject
  AmountDeadlineDynamicQuery dynamicQuery;

  DynamicQueryParameters<AmountDeadline> parameters = new DynamicQueryParameters<>();

  @Test
  void instantiateAmountDeadlineStatuses() {
    assertNotNull(new AmountDeadlineStatuses());
  }

  @Test
  void getMany() {
    parameters.projection().addNames(AmountDeadlineDto.JSON_IDENTIFIER,
        AmountDeadlineDto.JSON_DEADLINE_AS_STRING);
    assertEquals(true, dynamicQuery.getMany(parameters).size() > 0);
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/amountdeadlinedynamicquery.sql");
    }
  }
}
