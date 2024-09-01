package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters.ResultMode;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.cyk.system.poulsscolaire.server.api.fee.DeadlineDto;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(DeadlineDynamicQueryTest.Profile.class)
class DeadlineDynamicQueryTest {

  @Inject
  DeadlineDynamicQuery dynamicQuery;

  DynamicQueryParameters<Deadline> parameters = new DynamicQueryParameters<>();

  @Test
  void buildQueryString() {
    parameters.setResultMode(ResultMode.ONE);
    parameters.filter().addCriteria(DeadlineDto.JSON_IDENTIFIER, "1");
    assertEquals("SELECT t.identifier FROM Deadline t WHERE t.identifier = :identifiant",
        dynamicQuery.buildQueryString(parameters));
  }

  @Test
  void getMany() {
    parameters.projection().addNames(Deadline.FIELD_AS_STRING);
    assertEquals(1, dynamicQuery.getMany(parameters).size());
  }

  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/deadlinedynamicquery.sql");
    }
  }
}
