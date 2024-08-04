package org.cyk.system.poulsscolaire.server.impl.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ci.gouv.dgbf.extension.server.persistence.query.DynamicQueryParameters;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import java.util.Map;
import org.junit.jupiter.api.Test;

@QuarkusTest
@TestProfile(BranchDynamicQueryTest.Profile.class)
class BranchDynamicQueryTest {

  @Inject
  BranchDynamicQuery dynamicQuery;

  DynamicQueryParameters<Branch> parameters = new DynamicQueryParameters<>();

  @Test
  void getMany() {
    assertEquals(1, dynamicQuery.getMany(parameters).size());
  }
  
  public static class Profile implements QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {
      return Map.of("quarkus.hibernate-orm.sql-load-script", "sql/branchdynamicquery.sql");
    }
  }
}
